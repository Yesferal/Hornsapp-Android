package com.yesferal.hornsapp.app.presentation.ui.concert.search

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import com.yesferal.hornsapp.app.R
import com.yesferal.hornsapp.app.presentation.common.BaseFragment
import com.yesferal.hornsapp.app.presentation.common.ViewData
import com.yesferal.hornsapp.app.presentation.ui.concert.search.adapter.ConcertAdapter
import com.yesferal.hornsapp.app.presentation.ui.concert.detail.ConcertActivity
import com.yesferal.hornsapp.app.presentation.ui.concert.detail.EXTRA_PARAM_PARCELABLE
import com.yesferal.hornsapp.app.presentation.common.entity.asParcelable
import com.yesferal.hornsapp.app.presentation.common.custom.*
import com.yesferal.hornsapp.hada.container.resolve
import kotlinx.android.synthetic.main.custom_error.*
import kotlinx.android.synthetic.main.custom_view_progress_bar.*
import kotlinx.android.synthetic.main.fragment_concerts.*
import kotlinx.android.synthetic.main.fragment_concerts.stubView

class ConcertsFragment
    : BaseFragment<ConcertsViewState>() {

    private lateinit var concertAdapter: ConcertAdapter

    override val actionListener by lazy {
        container.resolve<ConcertsPresenter>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_concerts, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        concertAdapter = ConcertAdapter(instanceConcertAdapterListener())

        concertsRecyclerView.also {
            it.adapter = concertAdapter
            it.layoutManager = linearLayoutManagerVertical
            it.addItemDecoration(RecyclerViewVerticalDecorator())
        }

        val handler = Handler()
        handler.postDelayed({
            actionListener.onViewCreated()
        }, 333)
    }

    override fun render(viewState: ConcertsViewState) {
        viewState.concerts?.let { concerts ->
            showConcerts(concerts)
        }

        viewState.errorMessage?.let {
            showError(messageId =  viewState.errorMessage)
        }

        if (viewState.isLoading) {
            showProgress()
        } else {
            hideProgress()
        }
    }

    private fun showProgress() {
        customProgressBar.fadeIn()
    }

    private fun hideProgress() {
        customProgressBar.fadeOut()
    }

    private fun showConcerts(concerts: List<ConcertViewData>) {
        concertAdapter.setItem(concerts)
    }

    private fun showError(
        @StringRes messageId: Int
    ) {
        stubView.visibility = View.VISIBLE
        errorTextView.text = getString(messageId)
    }

    companion object {
        fun newInstance() = ConcertsFragment()
    }
}

private fun ConcertsFragment.instanceConcertAdapterListener() =
    object : ConcertAdapter.Listener {
        override fun onConcertClick(concert: ViewData) {
            val intent = Intent(
                activity,
                ConcertActivity::class.java
            )

            intent.putExtra(
                EXTRA_PARAM_PARCELABLE,
                concert.asParcelable()
            )

            startActivity(intent)
        }
    }