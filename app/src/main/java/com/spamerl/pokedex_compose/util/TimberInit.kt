package com.spamerl.pokedex_compose.util

import android.content.Context
import androidx.startup.Initializer
import com.spamerl.pokedex_compose.BuildConfig
import timber.log.Timber


class TimberInit : Initializer<Unit> {
    override fun create(context: Context) {
        if (!BuildConfig.DEBUG) return
        Timber.plant(Timber.DebugTree())
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}