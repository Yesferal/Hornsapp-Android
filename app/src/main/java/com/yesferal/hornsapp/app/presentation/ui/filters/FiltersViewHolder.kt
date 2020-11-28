package com.yesferal.hornsapp.app.presentation.ui.filters

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.yesferal.hornsapp.app.presentation.common.multitype.ViewHolderBinding
import com.yesferal.hornsapp.app.presentation.common.multitype.BaseViewHolder
import com.yesferal.hornsapp.app.presentation.common.custom.RecyclerViewHorizontalDecorator
import com.yesferal.hornsapp.app.presentation.ui.concert.upcoming.FiltersViewData
import kotlinx.android.synthetic.main.item_filters.view.*

class FiltersViewHolder(
    itemView: View,
    listener: ViewHolderBinding.Listener
) : BaseViewHolder<FiltersViewData>(itemView, listener) {

    private val filtersAdapter by lazy {
        CategoriesAdapter(instanceConcertsAdapterListener())
    }
    interface Listener {
        fun onClick(categoryViewData: CategoryViewData)
    }

    init {
        val linearLayoutManager = LinearLayoutManager(
            itemView.filtersRecyclerView.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        itemView.filtersRecyclerView.also {
            it.adapter = filtersAdapter
            it.layoutManager = linearLayoutManager
            it.addItemDecoration(RecyclerViewHorizontalDecorator(padding = 4))
        }
    }

    override fun bind(viewData: FiltersViewData) {
        filtersAdapter.setItems(viewData.categories)
    }

    private fun instanceConcertsAdapterListener() =
        object : CategoryViewHolder.Listener {
            override fun onClick(textViewData: CategoryViewData) {
                (listener as FiltersViewData.Listener).onClick(textViewData)
            }
        }
}