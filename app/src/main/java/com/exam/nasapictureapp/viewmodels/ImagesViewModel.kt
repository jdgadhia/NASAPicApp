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

class ImagesViewModel(
    imagesRepository: ImagesRepository
) : ViewModel() {

    private val fileName = MutableLiveData<String>()
    private lateinit var imagesList: ArrayList<ImagesDetails>

    fun getImagesList(name: String) {
        fileName.value = name
    }

    var getImagesListResponse = fileName.switchMap {
        liveData(Dispatchers.Default) {
            emit(imagesRepository.getImagesList(it))
        }
    }

    fun convertFromStringToData(stringData: String): ArrayList<ImagesDetails> {
        val imagesResponse: ImagesResponse? =
            provideGson().fromJson(stringData, ImagesResponse::class.java)
        return if (imagesResponse != null && !imagesResponse.isEmpty()) {
            Collections.sort(imagesResponse, kotlin.Comparator { image1, image2 ->
                stringToDate(image1.date).compareTo(stringToDate(image2.date))
            })
            imagesResponse
        } else {
            ArrayList()
        }
    }

    private fun stringToDate(strDate: String): Date {
        return SimpleDateFormat("yyyy-MM-dd").parse(strDate)
    }

    fun setImageList(imagesList: ArrayList<ImagesDetails>) {
        this.imagesList = imagesList
    }

    fun getImageList(): ArrayList<ImagesDetails> {
        return imagesList
    }
}