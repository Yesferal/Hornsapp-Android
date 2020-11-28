package com.yesferal.hornsapp.app.presentation.common.multitype

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<VIEW_DATA: ViewHolderBinding>(
    itemView: View,
    var listener: ViewHolderBinding.Listener? = null
) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(viewData: VIEW_DATA)
}