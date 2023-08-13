package com.imagecollagemaker.uis

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.imagecollagemaker.R
import com.imagecollagemaker.databinding.ActivityMainBinding
import com.imagecollagemaker.fragments.NumberOfImageDialog

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var firstImageUri: Uri? = null
    private var secondImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {
        setSupportActionBar(binding.toolbar)
        binding.ivFirstImage.setOnClickListener(this)
        binding.ivSecondImage.setOnClickListener(this)
        binding.btnCreateCollage.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        view?.let {
            when (it.id) {
                R.id.ivFirstImage -> {
                    firstImagePickerIntent.launch("image/*")
                }

                R.id.ivSecondImage -> {
                    secondImagePickerIntent.launch("image/*")
                }

                else -> {

                    var numberOfImageDialog =
                        supportFragmentManager.findFragmentByTag(NumberOfImageDialog.TAG) as? NumberOfImageDialog

                    if (numberOfImageDialog == null) {
                        numberOfImageDialog = NumberOfImageDialog()
                    }
                    numberOfImageDialog.callback = onNumberSelectedCallback


                    if (firstImageUri == null || secondImageUri == null) {
                        Toast.makeText(this@MainActivity, "Choose both images", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        numberOfImageDialog.show(supportFragmentManager, NumberOfImageDialog.TAG)
                    }
                }
            }
        }
    }

    private val onNumberSelectedCallback = object : NumberOfImageDialog.OnNumberSelectedListener {
        override fun onNumberSelected(number: Int) {
            if (number < 10) {
                Toast.makeText(
                    this@MainActivity,
                    "Number of image must be larger than 10.",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                val collageIntent = Intent(this@MainActivity, CollageActivity::class.java)
                collageIntent.putExtra(
                    CollageActivity.FIRST_IMAGE_URI,
                    firstImageUri.toString()
                )
                collageIntent.putExtra(
                    CollageActivity.SECOND_IMAGE_URI,
                    secondImageUri.toString()
                )
                collageIntent.putExtra(
                    CollageActivity.NUMBER_OF_IMAGES,
                    number
                )
                startActivity(collageIntent)
            }
        }
    }

    private val firstImagePickerIntent = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            firstImageUri = it
            binding.ivFirstImage.setImageURI(it)
        }
    }
    private val secondImagePickerIntent = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            secondImageUri = it
            binding.ivSecondImage.setImageURI(it)
        }
    }
}