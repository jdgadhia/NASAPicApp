package com.exam.nasapictureapp.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exam.nasapictureapp.R
import com.exam.nasapictureapp.model.ImagesDetails

class ImageGridAdepter(
    private val activity: Activity,
    private var images: ArrayList<ImagesDetails>
) : RecyclerView.Adapter<ImageGridAdepter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View =
            LayoutInflater.from(activity).inflate(R.layout.raw_image_grid, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(activity).load(images[position].url).into(holder.ivImage)
        holder.tvDate.text = images[position].date
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class MyViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var ivImage: ImageView = view.findViewById(R.id.ivImage)
        var tvDate: TextView = view.findViewById(R.id.tvDate)
    }

}