package com.yesferal.hornsapp.app.presentation.ui.concert.detail

import androidx.annotation.StringRes
import com.google.android.gms.ads.AdView
import com.yesferal.hornsapp.app.presentation.common.ViewData
import com.yesferal.hornsapp.app.presentation.common.ViewState
import com.yesferal.hornsapp.domain.entity.Venue
import java.net.URI
import java.util.*

data class ConcertViewState(
    val concert: ConcertViewData? = null,
    val bands: List<BandViewData>? = null,
    val adView: AdView? = null,
    val isLoading: Boolean = false,
    @StringRes val errorMessageId: Int? = null
) : ViewState()

class ConcertViewData(
    id: String,
    name: String?,
    val description: String?,
    val date: Date?,
    val dateTime: String?,
    val day: String?,
    val month: String?,
    val trailerUrl: URI?,
    val facebookUrl: URI?,
    var isFavorite: Boolean,
    val genre: String?,
    val ticketingHost: String? = null,
    val ticketingUrl: URI? = null,
    val venue: Venue? = null,
) : ViewData(id, name)

class BandViewData(
    id: String,
    name: String?,
    val membersImage: String?,
    val genre: String?
) : ViewData(id, name)