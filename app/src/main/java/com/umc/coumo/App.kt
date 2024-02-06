package com.umc.coumo

import android.app.Application
import com.umc.coumo.utils.PreferenceUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    companion object {
        lateinit var prefs: PreferenceUtil
    }
}
