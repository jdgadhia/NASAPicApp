package com.exam.nasapictureapp.interfaces

import android.view.View

interface ItemClickEvent {
    fun onClick(position: Int, viewTransition: View)
}