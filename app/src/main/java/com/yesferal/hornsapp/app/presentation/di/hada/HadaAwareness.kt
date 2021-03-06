package com.yesferal.hornsapp.app.presentation.di.hada

import android.app.Activity
import androidx.fragment.app.Fragment
import com.yesferal.hornsapp.hada.container.Container

interface HadaAwareness {
    fun Fragment.hada(): Container {
        return (requireActivity().application as HadaApp).container
    }

    fun Activity.hada(): Container {
        return (application as HadaApp).container
    }
}