package com.logic.difflang

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.logic.difflang.utils.LocalManager

class App : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocalManager.getPreferences(base!!))
    }
    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        LocalManager.getPreferences(this)
    }
}