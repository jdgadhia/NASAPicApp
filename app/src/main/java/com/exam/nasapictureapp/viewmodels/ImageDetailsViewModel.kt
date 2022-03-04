package com.exam.nasapictureapp.viewmodels

import androidx.lifecycle.*
import com.exam.nasapictureapp.di.provideGson
import com.exam.nasapictureapp.model.ImagesDetails
import com.exam.nasapictureapp.model.ImagesResponse
import com.exam.nasapictureapp.repository.ImagesRepository
import kotlinx.coroutines.Dispatchers
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class ImageDetailsViewModel(
) : ViewModel() {

    val imagesList = MutableLiveData<ArrayList<ImagesDetails>>()

    fun setImageList(imagesList: ArrayList<ImagesDetails>) {
        this.imagesList.value = imagesList
    }

}