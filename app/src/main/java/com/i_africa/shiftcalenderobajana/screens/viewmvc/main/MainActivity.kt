package com.i_africa.shiftcalenderobajana.screens.viewmvc.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.i_africa.shiftcalenderobajana.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calendarView.setOnDayClickListener{

        }

        binding.calendarView.setOnForwardPageChangeListener {

        }

        binding.calendarView.setOnPreviousPageChangeListener {

        }
    }
}