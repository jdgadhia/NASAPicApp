package com.exam.nasapictureapp.di

import com.exam.nasapictureapp.repository.ImagesRepository
import com.exam.nasapictureapp.viewmodels.ImagesViewModel
import com.google.gson.Gson
import org.koin.dsl.module

val appModule = module {
    single { provideGson() }

    factory { ImagesViewModel(get()) }
    factory { ImagesRepository() }

}

fun provideGson(): Gson {
    return Gson()
}