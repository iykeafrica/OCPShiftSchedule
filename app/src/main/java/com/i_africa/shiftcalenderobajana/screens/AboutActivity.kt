package com.i_africa.shiftcalenderobajana.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import com.i_africa.shiftcalenderobajana.R
import com.i_africa.shiftcalenderobajana.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.license.movementMethod = LinkMovementMethod.getInstance()
    }
}