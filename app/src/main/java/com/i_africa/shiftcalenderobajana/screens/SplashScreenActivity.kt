package com.i_africa.shiftcalenderobajana.screens

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import com.i_africa.shiftcalenderobajana.R
import com.i_africa.shiftcalenderobajana.databinding.ActivitySplashScreenBinding
import com.i_africa.shiftcalenderobajana.screens.selectshiftall.SelectShiftAllActivity

class SplashScreenActivity : AppCompatActivity() {

    lateinit var binding : ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        Handler().postDelayed(Runnable {
            startActivity(Intent(this, SelectShiftAllActivity::class.java))
            finish()
        }, 300)

    }

}