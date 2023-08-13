package com.imagecollagemaker.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imagecollagemaker.R

class CollageAdapter(private val firstImageUri: Uri?, private val secondImageUri: Uri?, private val numberOfImages: Int) :
    RecyclerView.Adapter<CollageAdapter.CollageViewHolder>() {

    private var previousPosition = 0
    private var increment = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollageViewHolder {
        return CollageViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_image, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CollageViewHolder, position: Int) {
        // 1, 3, 6, 10, 15, 21
        // 0, 1, 2, 3, 4, 5, 6
        // 0, 2, 5, 9, 14, 20
        //

        // position 0, 1, 2, 3, 4, 5, 6 ...

        holder.tvIndex.text = String.format("%d", position + 1)

        if (position == 0) {
            holder.ivCollage.setImageURI(firstImageUri)
        } else {
            if (position == (previousPosition + increment)) {
                previousPosition += increment
                increment++
                holder.ivCollage.setImageURI(firstImageUri)
            } else {
                holder.ivCollage.setImageURI(secondImageUri)
            }
        }

    }

    override fun getItemCount(): Int {
        return numberOfImages
    }

    inner class CollageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivCollage: ImageView = view.findViewById(R.id.ivCollage)
        val tvIndex: TextView = view.findViewById(R.id.tvIndex)
    }
}
