package com.imagecollagemaker.utils

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        Log.e(
            GridItemDecoration::class.java.simpleName,
            "Item position: ${parent.getChildLayoutPosition(view)}"
        )
        outRect.top = 8
        outRect.bottom = 8
        outRect.right = 8
        outRect.left = 8
    }
}