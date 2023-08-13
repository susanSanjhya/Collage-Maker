package com.imagecollagemaker.uis

import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imagecollagemaker.adapters.CollageAdapter
import com.imagecollagemaker.databinding.ActivityCollageBinding
import com.imagecollagemaker.utils.GridItemDecoration

class CollageActivity : AppCompatActivity() {

    companion object {
        const val FIRST_IMAGE_URI = "FIRST_IMAGE_URI"
        const val SECOND_IMAGE_URI = "SECOND_IMAGE_URI"
        const val NUMBER_OF_IMAGES = "NUMBER_OF_IMAGES"
    }

    private lateinit var binding: ActivityCollageBinding

    private var firstImageUri: Uri? = null
    private var secondImageUri: Uri? = null
    private var numberOfImages = 50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCollageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getImages()

        setupViews()
    }

    private fun setupViews() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.rvCollage.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        binding.rvCollage.addItemDecoration(GridItemDecoration())
        binding.rvCollage.adapter = CollageAdapter(firstImageUri, secondImageUri, numberOfImages)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun getImages() {
        firstImageUri = Uri.parse(intent.getStringExtra(FIRST_IMAGE_URI))
        secondImageUri = Uri.parse(intent.getStringExtra(SECOND_IMAGE_URI))
        numberOfImages = intent.getIntExtra(NUMBER_OF_IMAGES, 50)
    }

}
