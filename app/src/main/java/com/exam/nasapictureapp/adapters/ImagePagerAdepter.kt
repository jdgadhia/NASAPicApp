package com.exam.nasapictureapp.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.exam.nasapictureapp.R
import com.exam.nasapictureapp.interfaces.ItemClickEvent
import com.exam.nasapictureapp.model.ImagesDetails

class ImagePagerAdepter(
    private val activity: Activity,
    private var images: ArrayList<ImagesDetails>
) : RecyclerView.Adapter<ImagePagerAdepter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View =
            LayoutInflater.from(activity).inflate(R.layout.raw_image_pager, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(activity)
            .load(images[position].hdurl)
            .into(holder.ivImage)
        holder.tvDate.text = images[position].date
        holder.tvName.text = images[position].title
        holder.tvInfo.text = images[position].explanation
        if (images[position].copyright.isNullOrEmpty()) {
            holder.tvCopyRight.visibility = View.GONE
        } else {
            holder.tvCopyRight.visibility = View.VISIBLE
            holder.tvCopyRight.text = images[position].copyright
        }

    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class MyViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var ivImage: ImageView = view.findViewById(R.id.ivImage)
        var tvDate: TextView = view.findViewById(R.id.tvDate)
        var tvName: TextView = view.findViewById(R.id.tvName)
        var tvInfo: TextView = view.findViewById(R.id.tvInfo)
        var tvCopyRight: TextView = view.findViewById(R.id.tvCopyRight)
    }

}