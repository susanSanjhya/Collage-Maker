package com.imagecollagemaker.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.DialogFragment
import com.imagecollagemaker.R

class NumberOfImageDialog : DialogFragment(), View.OnClickListener {

    companion object {
        const val TAG = "NumberOfImageDialog"
    }

    var callback: OnNumberSelectedListener? = null
    lateinit var etNumberOfImages: AppCompatEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        return inflater.inflate(R.layout.fragment_number_of_images, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.btnCancel).setOnClickListener(this@NumberOfImageDialog)
        view.findViewById<Button>(R.id.btnCreate).setOnClickListener(this@NumberOfImageDialog)
        etNumberOfImages = view.findViewById(R.id.etNumberOfImages)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnCancel -> {
                dismiss()
            }

            R.id.btnCreate -> {
                callback?.onNumberSelected(
                    if (etNumberOfImages.text.toString().isNotBlank()) {
                        etNumberOfImages.text.toString().toInt()
                    } else {
                        50
                    }
                )
                dismiss()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnNumberSelectedListener) {
            callback = context
        }
    }

    override fun onDetach() {
        callback = null
        super.onDetach()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCancelable(false)
        return dialog
    }

    interface OnNumberSelectedListener {
        fun onNumberSelected(number: Int)
    }
}
