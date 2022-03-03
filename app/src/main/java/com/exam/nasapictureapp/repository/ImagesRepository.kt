package com.exam.nasapictureapp.repository

import com.exam.nasapictureapp.NasaPicturesApplication

open class ImagesRepository() {

    suspend fun getImagesList(fileName : String): String {
        return try {
            NasaPicturesApplication.appContext.assets.open(fileName).bufferedReader().use{
                it.readText()
            }
        } catch (e: Exception) {
            ""
        }
    }
}