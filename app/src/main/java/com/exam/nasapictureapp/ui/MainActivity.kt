package com.exam.nasapictureapp.ui

import android.content.Intent
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
import com.exam.nasapictureapp.di.provideGson
import com.exam.nasapictureapp.interfaces.ItemClickEvent
import com.exam.nasapictureapp.model.ImagesDetails
import com.exam.nasapictureapp.viewmodels.ImagesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), ItemClickEvent {
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

        adapter = ImageGridAdepter(this, imagesList, this)
        binding.rvImages.adapter = adapter

        imagesViewModel.setImageList(imagesList)
    }

    // Click event handle of recycler view
    override fun onClick(position: Int) {
        val intent = Intent(this@MainActivity, ImageDetailsActivity::class.java)
        intent.putExtra("position", position)
        val listData = provideGson().toJson(imagesViewModel.getImageList())
        intent.putExtra("images", listData)
        startActivity(intent)
    }
}