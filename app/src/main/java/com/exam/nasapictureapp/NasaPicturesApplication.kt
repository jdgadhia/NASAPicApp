package com.exam.nasapictureapp

import android.app.Application
import android.content.Context
import com.exam.nasapictureapp.di.appModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NasaPicturesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        appContext = this

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger(Level.ERROR)
            }
            androidContext(this@NasaPicturesApplication)
            modules(listOf(appModule))
        }
    }

    fun getContext(): Context {
        return applicationContext
    }

    companion object {
        private var instance: NasaPicturesApplication? = null
        lateinit var appContext: Context

        fun getInstance(): NasaPicturesApplication? {
            return instance
        }
    }
}