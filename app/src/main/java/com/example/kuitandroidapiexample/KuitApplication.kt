package com.example.kuitandroidapiexample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltAndroidApp
class KuitApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}