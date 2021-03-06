package com.yesferal.hornsapp.app.presentation.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yesferal.hornsapp.app.R
import com.yesferal.hornsapp.app.presentation.common.base.BaseFragment
import com.yesferal.hornsapp.app.presentation.common.extension.fadeIn
import com.yesferal.hornsapp.app.presentation.common.extension.fadeOut
import kotlinx.android.synthetic.main.fragment_on_boarding.*

class OnBoardingFragment
    : BaseFragment <OnBoardingViewState>() {
    private lateinit var onBoardingViewModel: OnBoardingViewModel

    override val layout: Int
        get() = R.layout.fragment_on_boarding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBoardingViewModel = ViewModelProvider(
            this,
            hada().resolve<OnBoardingViewModelFactory>()
        ).get(OnBoardingViewModel::class.java)

        onBoardingViewModel.state.observe(viewLifecycleOwner) {
            render(it)
        }

        nextTextView.setOnClickListener {
            onBoardingViewModel.updateVisibilityOnBoarding()
            findNavController().navigate(OnBoardingFragmentDirections.actionOnBoardingToHome())
        }
    }

    override fun render(viewState: OnBoardingViewState) {
        viewState.onBoardingViewData?.let {
            showData(it)
        }

        if (viewState.isLoading) {
            showProgress()
        } else {
            hideProgress()
        }
    }

    private fun showData(onBoardingViewData: OnBoardingViewData) {
        metalCard.setText(onBoardingViewData.metalConcerts.toString(), getString(R.string.metal_category))
        rockCard.setText(onBoardingViewData.rockConcerts.toString(), getString(R.string.rock_category))
        upcomingCard.setText(onBoardingViewData.upcomingConcerts.toString(), getString(R.string.upcoming_category))
        totalCard.setText(onBoardingViewData.total.toString(), getString(R.string.total_category))
    }

    private fun showProgress() {
        progressBar.fadeIn()
        nextTextView.fadeOut()
    }

    private fun hideProgress() {
        progressBar.fadeOut()
        nextTextView.fadeIn()
    }
}