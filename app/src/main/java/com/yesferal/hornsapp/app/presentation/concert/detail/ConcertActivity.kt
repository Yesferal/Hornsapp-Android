package com.yesferal.hornsapp.app.presentation.concert.detail

import android.net.Uri
import android.os.Bundle
import android.view.View
import com.google.android.gms.ads.AdView
import com.yesferal.hornsapp.app.R
import com.yesferal.hornsapp.app.presentation.common.BaseActivity
import com.yesferal.hornsapp.app.presentation.concert.ConcertParcelable
import com.yesferal.hornsapp.app.util.load
import com.yesferal.hornsapp.app.util.setUpWith
import com.yesferal.hornsapp.domain.entity.Local
import kotlinx.android.synthetic.main.activity_concert.*

const val EXTRA_PARAM_PARCELABLE = "EXTRA_PARAM_PARCELABLE"

class ConcertActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_concert)

        val concert = intent?.extras?.getParcelable<ConcertParcelable>(
            EXTRA_PARAM_PARCELABLE
        )

        if (concert == null) {
            this.finish()
            return
        }

        if (savedInstanceState == null) {
            val concertFragment = ConcertFragment.newInstance(concert).apply {
                listener = instanceConcertFragmentListener()
            }
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragmentContainerLayout,
                    concertFragment
                )
                .commit()
        }

        concertImageView.load(concert.posterImage)
        favoriteImageView.isChecked = concert.isFavorite
        favoriteImageView.setOnCheckedChangeListener { isChecked ->
            concert.isFavorite = isChecked
        }

        titleTextView.setUpWith(concert.name)
        titleToolbarTextView.setUpWith(concert.name)

        closeImageView.setOnClickListener {
            finish()
        }
    }
}

private fun ConcertActivity.instanceConcertFragmentListener() =
    object : ConcertFragment.Listener {
        override fun show(adView: AdView) {
            adContainerLayout.removeAllViews()
            adContainerLayout.addView(adView)
        }

        override fun show(local: Local) {
            locationImageView.visibility = View.VISIBLE
            locationImageView.setOnClickListener {
                val latitude = local.latitude
                val longitude = local.longitude
                val uri = Uri.parse("geo:${latitude},${longitude}?q=${Uri.encode(local.name)}")
                startExternalActivity(uri, getString(R.string.maps_package))
            }
        }
    }