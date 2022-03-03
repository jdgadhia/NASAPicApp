package com.exam.nasapictureapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.exam.nasapictureapp.R
import com.exam.nasapictureapp.adapters.ImageGridAdepter
import com.exam.nasapictureapp.databinding.ActivityMainBinding
import com.exam.nasapictureapp.model.ImagesDetails
import com.exam.nasapictureapp.viewmodels.ImagesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val imagesViewModel: ImagesViewModel by viewModel()
    lateinit var adapter: ImageGridAdepter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setImagesListener()
        imagesViewModel.getImagesList("data.json")
    }

    private fun setImagesListener() {
        imagesViewModel.getImagesListResponse.observe(this, { images ->
            if (images != null) {
                Log.e("Images", images)
                val imagesList: ArrayList<ImagesDetails> =
                    imagesViewModel.convertFromStringToData(images)
                if (imagesList.isEmpty()) {
                    binding.tvNoDataMessage.visibility = View.VISIBLE
                    binding.rvImages.visibility = View.GONE
                } else {
                    binding.tvNoDataMessage.visibility = View.GONE
                    binding.rvImages.visibility = View.VISIBLE
                    setImagesInList(imagesList)
                }
            } else {
                binding.tvNoDataMessage.visibility = View.VISIBLE
                binding.rvImages.visibility = View.GONE
            }
        })
    }

    private fun setImagesInList(imagesList: ArrayList<ImagesDetails>) {
        val layoutManager = GridLayoutManager(this, 2)
        binding.rvImages.layoutManager = layoutManager

        val controller = AnimationUtils.loadLayoutAnimation(this, R.anim.image_grid_animation);
        binding.rvImages.layoutAnimation = controller

        adapter = ImageGridAdepter(this, imagesList)
        binding.rvImages.adapter = adapter
    }
}