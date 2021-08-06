package com.dynamicdevz.bottlerocket.di

import android.app.Application

class WeatherApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        DI.context = this
    }
}