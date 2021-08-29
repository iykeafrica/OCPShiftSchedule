package com.i_africa.shiftcalenderobajana.screens.selectshiftall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.i_africa.shiftcalenderobajana.databinding.ActivitySelectShiftAllBinding

class SelectShiftAllActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectShiftAllBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectShiftAllBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.twoDaysShift?.setOnClickListener {
            binding.threeDaysChipGroup?.visibility = View.GONE
            binding.fourDaysChipGroup?.visibility = View.GONE
            binding.twoDaysChipGroup?.visibility = View.VISIBLE

            binding.plantATwo?.setOnClickListener {
                Toast.makeText(this, "Plant A selected", Toast.LENGTH_SHORT).show()
            }

            binding.plantBTwo?.setOnClickListener {
                Toast.makeText(this, "Plant B selected", Toast.LENGTH_SHORT).show()
            }

            binding.plantCTwo?.setOnClickListener {
                Toast.makeText(this, "Plant C selected", Toast.LENGTH_SHORT).show()
            }

            binding.plantDTwo?.setOnClickListener {
                Toast.makeText(this, "Plant D selected", Toast.LENGTH_SHORT).show()
            }

            binding.plantETwo?.setOnClickListener {
                Toast.makeText(this, "Plant E selected", Toast.LENGTH_SHORT).show()
            }

            binding.plantFTwo?.setOnClickListener {
                Toast.makeText(this, "Plant F selected", Toast.LENGTH_SHORT).show()
            }
        }

        binding.threeDaysShift?.setOnClickListener {
            binding.fourDaysChipGroup?.visibility = View.GONE
            binding.twoDaysChipGroup?.visibility = View.GONE
            binding.threeDaysChipGroup?.visibility = View.VISIBLE

            binding.button?.setOnClickListener {
                Toast.makeText(this, "Plant A selected", Toast.LENGTH_SHORT).show()
            }
            binding.button2?.setOnClickListener {
                Toast.makeText(this, "Plant B selected", Toast.LENGTH_SHORT).show()
            }
            binding.button3?.setOnClickListener {
                Toast.makeText(this, "Plant C selected", Toast.LENGTH_SHORT).show()
            }
            binding.button4?.setOnClickListener {
                Toast.makeText(this, "CMTCE A selected", Toast.LENGTH_SHORT).show()
            }
            binding.button5?.setOnClickListener {
                Toast.makeText(this, "CMTCE B selected", Toast.LENGTH_SHORT).show()
            }
            binding.button6?.setOnClickListener {
                Toast.makeText(this, "CMTCE C selected", Toast.LENGTH_SHORT).show()
            }
            binding.button7?.setOnClickListener {
                Toast.makeText(this, "Security A selected", Toast.LENGTH_SHORT).show()
            }
            binding.button8?.setOnClickListener {
                Toast.makeText(this, "Security B selected", Toast.LENGTH_SHORT).show()
            }
            binding.button9?.setOnClickListener {
                Toast.makeText(this, "Security C selected", Toast.LENGTH_SHORT).show()
            }
        }

        binding.fourDaysShift?.setOnClickListener {
            binding.twoDaysChipGroup?.visibility = View.GONE
            binding.threeDaysChipGroup?.visibility = View.GONE
            binding.fourDaysChipGroup?.visibility = View.VISIBLE


            binding.plantAFour?.setOnClickListener {
                Toast.makeText(this, "Plant A selected", Toast.LENGTH_SHORT).show()
            }

            binding.plantBFour?.setOnClickListener {
                Toast.makeText(this, "Plant B selected", Toast.LENGTH_SHORT).show()
            }

            binding.plantCFour?.setOnClickListener {
                Toast.makeText(this, "Plant C selected", Toast.LENGTH_SHORT).show()
            }

            binding.plantDFour?.setOnClickListener {
                Toast.makeText(this, "Plant D selected", Toast.LENGTH_SHORT).show()
            }

            binding.plantEFour?.setOnClickListener {
                Toast.makeText(this, "Plant E selected", Toast.LENGTH_SHORT).show()
            }

            binding.plantFFour?.setOnClickListener {
                Toast.makeText(this, "Plant F selected", Toast.LENGTH_SHORT).show()
            }

            binding.plantGFour?.setOnClickListener {
                Toast.makeText(this, "Plant G selected", Toast.LENGTH_SHORT).show()
            }

            binding.plantHFour?.setOnClickListener {
                Toast.makeText(this, "Plant H selected", Toast.LENGTH_SHORT).show()
            }

            binding.plantIFour?.setOnClickListener {
                Toast.makeText(this, "Plant I selected", Toast.LENGTH_SHORT).show()
            }

            binding.plantJFour?.setOnClickListener {
                Toast.makeText(this, "Plant J selected", Toast.LENGTH_SHORT).show()
            }

            binding.plantKFour?.setOnClickListener {
                Toast.makeText(this, "Plant K selected", Toast.LENGTH_SHORT).show()
            }

            binding.plantLFour?.setOnClickListener {
                Toast.makeText(this, "Plant L selected", Toast.LENGTH_SHORT).show()
            }
        }
    }
}