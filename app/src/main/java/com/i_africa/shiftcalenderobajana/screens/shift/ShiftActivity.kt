package com.i_africa.shiftcalenderobajana.screens.shift

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.i_africa.shiftcalenderobajana.R
import com.i_africa.shiftcalenderobajana.databinding.ActivityShiftBinding
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant.SHIFT_EXTRA_KEY
import com.i_africa.shiftcalenderobajana.screens.selectshift.SelectShiftViewMvc

class ShiftActivity : AppCompatActivity() {

    lateinit var binding: ActivityShiftBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShiftBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shift = intent.getStringExtra(Constant.PLANT_SHIFT_A)
        Log.d("TAG", "onCreate: $shift")
    }

    companion object {
        fun showShift(context: Context, shift: String) {
            val intent = Intent(context, ShiftActivity::class.java)
            intent.putExtra(shift, SHIFT_EXTRA_KEY)
            context.startActivity(intent)
        }
    }
}