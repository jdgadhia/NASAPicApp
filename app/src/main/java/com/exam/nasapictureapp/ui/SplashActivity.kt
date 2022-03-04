package com.exam.nasapictureapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.exam.nasapictureapp.R
import com.exam.nasapictureapp.databinding.ActivityImageDetailsBinding
import com.exam.nasapictureapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private var delayHandler: Handler? = null
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initializing the Handler
        delayHandler = Handler(Looper.myLooper()!!)

        //Navigate with delay
        delayHandler!!.postDelayed(
            runnable,
            SPLASH_DELAY
        )
    }

    private val runnable: Runnable = Runnable {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val SPLASH_DELAY: Long = 3000 //3 seconds
    }
}