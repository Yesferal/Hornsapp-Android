package com.yesferal.hornsapp.app.presentation.common.custom

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewVerticalDecorator (
    private val padding: Int = 8
): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.context == null) { return }

        val itemPosition = parent.getChildAdapterPosition(view)
        val density = parent.context.resources.displayMetrics.density

        if (itemPosition == 0) {
            outRect.top = (density * padding).toInt()
        }

        val adapter = parent.adapter
        if (adapter != null && itemPosition == adapter.itemCount - 1) {
            val adHeight = 50
            outRect.bottom = (density * (padding + adHeight)).toInt()
        }
    }
}