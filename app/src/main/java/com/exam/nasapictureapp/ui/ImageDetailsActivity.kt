package com.exam.nasapictureapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.exam.nasapictureapp.R
import com.exam.nasapictureapp.adapters.ImagePagerAdepter
import com.exam.nasapictureapp.databinding.ActivityImageDetailsBinding
import com.exam.nasapictureapp.databinding.ActivityMainBinding
import com.exam.nasapictureapp.di.provideGson
import com.exam.nasapictureapp.model.ImagesDetails
import com.exam.nasapictureapp.viewmodels.ImageDetailsViewModel
import com.exam.nasapictureapp.viewmodels.ImagesViewModel
import com.google.gson.reflect.TypeToken
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImageDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityImageDetailsBinding
    private val imageDetailsViewModel: ImageDetailsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchImagesFromIntent()
        setViewPagerData()
    }

    private fun fetchImagesFromIntent() {
        val listData = intent.getStringExtra("images")
        val typeToken = object : TypeToken<List<ImagesDetails>>() {}.type
        val images = provideGson().fromJson<List<ImagesDetails>>(
            listData,
            typeToken
        ) as ArrayList<ImagesDetails>
        imageDetailsViewModel.setImageList(images)
    }

    private fun setViewPagerData() {
        imageDetailsViewModel.imagesList.observe(this, { images ->
            if (images != null && images.isNotEmpty()) {
                binding.tvNoDataMessage.visibility = View.GONE
                binding.vpImages.visibility = View.VISIBLE

                val adapter = ImagePagerAdepter(this@ImageDetailsActivity, images)
                binding.vpImages.adapter = adapter

                binding.vpImages.setCurrentItem(intent.getIntExtra("position", 0), false)
            } else {
                binding.tvNoDataMessage.visibility = View.VISIBLE
                binding.vpImages.visibility = View.GONE
            }
        })
    }
}