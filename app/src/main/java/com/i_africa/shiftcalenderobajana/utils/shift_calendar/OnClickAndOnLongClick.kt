//package com.i_africa.shiftcalenderobajana.utils.shift_calendar
//
//import com.i_africa.shiftcalenderobajana.databinding.ActivityCustomShiftBinding
//import com.i_africa.shiftcalenderobajana.screens.customshift.CustomShiftViewMvc
//import com.i_africa.shiftcalenderobajana.screens.viewmvc.BaseViewMvc
//
//object OnClickAndOnLongClick: BaseViewMvc<CustomShiftViewMvc.Listener>()  {
//    fun onClick(binding: ActivityCustomShiftBinding) {
//        binding.one.setOnClickListener {
//            if (binding.one.text.toString().trim().isNotEmpty()) {
//                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//                for (listener in listeners) {
//                    listener.oneClick()
//                }
//            }
//        }
//
//        binding.two.setOnClickListener {
//            if (binding.two.text.toString().trim().isNotEmpty()) {
//                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//                for (listener in listeners) {
//                    listener.twoClick()
//                }
//            }
//        }
//
//        binding.three.setOnClickListener {
//            if (binding.three.text.toString().trim().isNotEmpty()) {
//                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//                for (listener in listeners) {
//                    listener.threeClick()
//                }
//            }
//        }
//
//        binding.four.setOnClickListener {
//            if (binding.four.text.toString().trim().isNotEmpty()) {
//                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//                for (listener in listeners) {
//                    listener.fourClick()
//                }
//            }
//        }
//
//        binding.five.setOnClickListener {
//            if (binding.five.text.toString().trim().isNotEmpty()) {
//                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//                for (listener in listeners) {
//                    listener.fiveClick()
//                }
//            }
//        }
//
//        binding.six.setOnClickListener {
//            if (binding.six.text.toString().trim().isNotEmpty()) {
//                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//                for (listener in listeners) {
//                    listener.sixClick()
//                }
//            }
//        }
//
//        binding.seven.setOnClickListener {
//            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//            for (listener in listeners) {
//                listener.sevenClick()
//            }
//
//        }
//
//        binding.eight.setOnClickListener {
//            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//            for (listener in listeners) {
//                listener.eightClick()
//            }
//        }
//
//        binding.nine.setOnClickListener {
//            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//            for (listener in listeners) {
//                listener.nineClick()
//            }
//        }
//
//        binding.ten.setOnClickListener {
//            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//            for (listener in listeners) {
//                listener.tenClick()
//            }
//        }
//
//        binding.eleven.setOnClickListener {
//            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//            for (listener in listeners) {
//                listener.elevenClick()
//            }
//        }
//
//        binding.twelve.setOnClickListener {
//            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//            for (listener in listeners) {
//                listener.twelveClick()
//            }
//        }
//
//        binding.thirteen.setOnClickListener {
//            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//            for (listener in listeners) {
//                listener.thirteenClick()
//            }
//        }
//
//        binding.fourteen.setOnClickListener {
//            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//            for (listener in listeners) {
//                listener.fourteenClick()
//            }
//        }
//
//        binding.fifteen.setOnClickListener {
//            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//            for (listener in listeners) {
//                listener.fifteenClick()
//            }
//        }
//
//        binding.sixteen.setOnClickListener {
//            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//            for (listener in listeners) {
//                listener.sixteenClick()
//            }
//        }
//
//        binding.seventeen.setOnClickListener {
//            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//            for (listener in listeners) {
//                listener.seventeenClick()
//            }
//        }
//
//        binding.eighteen.setOnClickListener {
//            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//            for (listener in listeners) {
//                listener.eighteenClick()
//            }
//        }
//
//        binding.nineteen.setOnClickListener {
//            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//            for (listener in listeners) {
//                listener.nineteenClick()
//            }
//        }
//
//        binding.twenty.setOnClickListener {
//            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//            for (listener in listeners) {
//                listener.twentyClick()
//            }
//        }
//
//        binding.twentyone.setOnClickListener {
//            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//            for (listener in listeners) {
//                listener.twentyOneClick()
//            }
//        }
//
//        binding.twentytwo.setOnClickListener {
//            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//            for (listener in listeners) {
//                listener.twentyTwoClick()
//            }
//        }
//
//        binding.twentythree.setOnClickListener {
//            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//            for (listener in listeners) {
//                listener.twentyThreeClick()
//            }
//        }
//
//        binding.twentyfour.setOnClickListener {
//            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//            for (listener in listeners) {
//                listener.twentyFourClick()
//            }
//        }
//
//        binding.twentyfive.setOnClickListener {
//            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//            for (listener in listeners) {
//                listener.twentyFiveClick()
//            }
//        }
//
//        binding.twentysix.setOnClickListener {
//            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//            for (listener in listeners) {
//                listener.twentySixClick()
//            }
//        }
//
//        binding.twentyseven.setOnClickListener {
//            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//            for (listener in listeners) {
//                listener.twentySevenClick()
//            }
//        }
//
//        binding.twentyeight.setOnClickListener {
//            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//            for (listener in listeners) {
//                listener.twentyEightClick()
//            }
//        }
//
//        binding.twentynine.setOnClickListener {
//            if (binding.twentynine.text.toString().trim().isNotEmpty()) {
//                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//                for (listener in listeners) {
//                    listener.twentyNineClick()
//                }
//            }
//        }
//
//        binding.thirty.setOnClickListener {
//            if (binding.thirty.text.toString().trim().isNotEmpty()) {
//                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//                for (listener in listeners) {
//                    listener.thirtyClick()
//                }
//            }
//        }
//
//        binding.thirtyone.setOnClickListener {
//            if (binding.thirtyone.text.toString().trim().isNotEmpty()) {
//                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//                for (listener in listeners) {
//                    listener.thirtyOneClick()
//                }
//            }
//        }
//
//        binding.thirtytwo.setOnClickListener {
//            if (binding.thirtytwo.text.toString().trim().isNotEmpty()) {
//                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//                for (listener in listeners) {
//                    listener.thirtyTwoClick()
//                }
//            }
//        }
//
//        binding.thirtythree.setOnClickListener {
//            if (binding.thirtythree.text.toString().trim().isNotEmpty()) {
//                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//                for (listener in listeners) {
//                    listener.thirtyThreeClick()
//                }
//            }
//        }
//
//        binding.thirtyfour.setOnClickListener {
//            if (binding.thirtyfour.text.toString().trim().isNotEmpty()) {
//                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//                for (listener in listeners) {
//                    listener.thirtyFourClick()
//                }
//            }
//        }
//
//        binding.thirtyfive.setOnClickListener {
//            if (binding.thirtyfive.text.toString().trim().isNotEmpty()) {
//                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//                for (listener in listeners) {
//                    listener.thirtyFiveClick()
//                }
//            }
//        }
//
//        binding.thirtysix.setOnClickListener {
//            if (binding.thirtysix.text.toString().trim().isNotEmpty()) {
//                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//                for (listener in listeners) {
//                    listener.thirtySixClick()
//                }
//            }
//        }
//
//        binding.thirtyseven.setOnClickListener {
//            if (binding.thirtyseven.text.toString().trim().isNotEmpty()) {
//                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//                for (listener in listeners) {
//                    listener.thirtySevenClick()
//                }
//            }
//        }
//    }
//
//    //Long CLick Listener
//    fun onLongClick(binding: ActivityCustomShiftBinding) {
//        binding.one.setOnLongClickListener {
//            val returnValue: Boolean = if (binding.one.text.toString().trim().isNotEmpty()) {
//                for (listener in listeners) {
//                    listener.popUpMenuClick2(it)
//                }
//                true
//            } else false
//            returnValue
//        }
//
//        binding.two.setOnLongClickListener {
//            val returnValue: Boolean = if (binding.two.text.toString().trim().isNotEmpty()) {
//                for (listener in listeners) {
//                    listener.popUpMenuClick2(it)
//                }
//                true
//            } else false
//            returnValue
//        }
//
//        binding.three.setOnLongClickListener {
//            val returnValue: Boolean = if (binding.three.text.toString().trim().isNotEmpty()) {
//                for (listener in listeners) {
//                    listener.popUpMenuClick2(it)
//                }
//                true
//            } else false
//            returnValue
//        }
//
//        binding.four.setOnLongClickListener {
//            val returnValue: Boolean = if (binding.four.text.toString().trim().isNotEmpty()) {
//                for (listener in listeners) {
//                    listener.popUpMenuClick2(it)
//                }
//                true
//            } else false
//            returnValue
//        }
//
//        binding.five.setOnLongClickListener {
//            val returnValue: Boolean = if (binding.five.text.toString().trim().isNotEmpty()) {
//                for (listener in listeners) {
//                    listener.popUpMenuClick2(it)
//                }
//                true
//            } else false
//            returnValue
//        }
//
//        binding.six.setOnLongClickListener {
//            val returnValue: Boolean = if (binding.six.text.toString().trim().isNotEmpty()) {
//                for (listener in listeners) {
//                    listener.popUpMenuClick2(it)
//                }
//                true
//            } else false
//            returnValue
//        }
//
//        binding.seven.setOnLongClickListener {
//            for (listener in listeners) {
//                listener.popUpMenuClick2(it)
//            }
//            true
//        }
//
//        binding.eight.setOnLongClickListener {
//            for (listener in listeners) {
//                listener.popUpMenuClick2(it)
//            }
//            true
//        }
//
//        binding.nine.setOnLongClickListener {
//            for (listener in listeners) {
//                listener.popUpMenuClick2(it)
//            }
//            true
//        }
//
//        binding.ten.setOnLongClickListener {
//            for (listener in listeners) {
//                listener.popUpMenuClick2(it)
//            }
//            true
//        }
//
//        binding.eleven.setOnLongClickListener {
//            for (listener in listeners) {
//                listener.popUpMenuClick2(it)
//            }
//            true
//        }
//
//        binding.twelve.setOnLongClickListener {
//            for (listener in listeners) {
//                listener.popUpMenuClick2(it)
//            }
//            true
//        }
//
//        binding.thirteen.setOnLongClickListener {
//            for (listener in listeners) {
//                listener.popUpMenuClick2(it)
//            }
//            true
//        }
//
//        binding.fourteen.setOnLongClickListener {
//            for (listener in listeners) {
//                listener.popUpMenuClick2(it)
//            }
//            true
//        }
//
//        binding.fifteen.setOnLongClickListener {
//            for (listener in listeners) {
//                listener.popUpMenuClick2(it)
//            }
//            true
//        }
//
//        binding.sixteen.setOnLongClickListener {
//            for (listener in listeners) {
//                listener.popUpMenuClick2(it)
//            }
//            true
//        }
//
//        binding.seventeen.setOnLongClickListener {
//            for (listener in listeners) {
//                listener.popUpMenuClick2(it)
//            }
//            true
//        }
//
//        binding.eighteen.setOnLongClickListener {
//            for (listener in listeners) {
//                listener.popUpMenuClick2(it)
//            }
//            true
//        }
//
//        binding.nineteen.setOnLongClickListener {
//            for (listener in listeners) {
//                listener.popUpMenuClick2(it)
//            }
//            true
//        }
//
//        binding.twenty.setOnLongClickListener {
//            for (listener in listeners) {
//                listener.popUpMenuClick2(it)
//            }
//            true
//        }
//
//        binding.twentyone.setOnLongClickListener {
//            for (listener in listeners) {
//                listener.popUpMenuClick2(it)
//            }
//            true
//        }
//
//        binding.twentytwo.setOnLongClickListener {
//            for (listener in listeners) {
//                listener.popUpMenuClick2(it)
//            }
//            true
//        }
//
//        binding.twentythree.setOnLongClickListener {
//            for (listener in listeners) {
//                listener.popUpMenuClick2(it)
//            }
//            true
//        }
//
//        binding.twentyfour.setOnLongClickListener {
//            for (listener in listeners) {
//                listener.popUpMenuClick2(it)
//            }
//            true
//        }
//
//        binding.twentyfive.setOnLongClickListener {
//            for (listener in listeners) {
//                listener.popUpMenuClick2(it)
//            }
//            true
//        }
//
//        binding.twentysix.setOnLongClickListener {
//            for (listener in listeners) {
//                listener.popUpMenuClick2(it)
//            }
//            true
//        }
//
//        binding.twentyseven.setOnLongClickListener {
//            for (listener in listeners) {
//                listener.popUpMenuClick2(it)
//            }
//            true
//        }
//
//        binding.twentyeight.setOnLongClickListener {
//            for (listener in listeners) {
//                listener.popUpMenuClick2(it)
//            }
//            true
//        }
//
//        binding.twentynine.setOnLongClickListener {
//            val returnValue: Boolean = if (binding.twentynine.text.toString().trim().isNotEmpty()) {
//                for (listener in listeners) {
//                    listener.popUpMenuClick2(it)
//                }
//                true
//            } else false
//            returnValue
//        }
//
//        binding.thirty.setOnLongClickListener {
//            val returnValue: Boolean = if (binding.thirty.text.toString().trim().isNotEmpty()) {
//                for (listener in listeners) {
//                    listener.popUpMenuClick2(it)
//                }
//                true
//            } else false
//            returnValue
//        }
//
//        binding.thirtyone.setOnLongClickListener {
//            val returnValue: Boolean = if (binding.thirtyone.text.toString().trim().isNotEmpty()) {
//                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//                for (listener in listeners) {
//                    listener.popUpMenuClick2(it)
//                }
//                true
//            } else false
//            returnValue
//        }
//
//        binding.thirtytwo.setOnLongClickListener {
//            val returnValue: Boolean = if (binding.thirtytwo.text.toString().trim().isNotEmpty()) {
//                for (listener in listeners) {
//                    listener.popUpMenuClick2(it)
//                }
//                true
//            } else false
//            returnValue
//        }
//
//        binding.thirtythree.setOnLongClickListener {
//            val returnValue: Boolean = if (binding.thirtythree.text.toString().trim().isNotEmpty()) {
//                for (listener in listeners) {
//                    listener.popUpMenuClick2(it)
//                }
//                true
//            } else false
//            returnValue
//        }
//
//        binding.thirtyfour.setOnLongClickListener {
//            val returnValue: Boolean = if (binding.thirtyfour.text.toString().trim().isNotEmpty()) {
//                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//                for (listener in listeners) {
//                    listener.popUpMenuClick2(it)
//                }
//                true
//            } else false
//            returnValue
//        }
//
//        binding.thirtyfive.setOnLongClickListener {
//            val returnValue: Boolean = if (binding.thirtyfive.text.toString().trim().isNotEmpty()) {
//                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//                for (listener in listeners) {
//                    listener.popUpMenuClick2(it)
//                }
//                true
//            } else false
//            returnValue
//        }
//
//        binding.thirtysix.setOnLongClickListener {
//            val returnValue: Boolean = if (binding.thirtysix.text.toString().trim().isNotEmpty()) {
//                for (listener in listeners) {
//                    listener.popUpMenuClick2(it)
//                }
//                true
//            } else false
//            returnValue
//        }
//
//        binding.thirtyseven.setOnLongClickListener {
//            val returnValue: Boolean = if (binding.thirtyseven.text.toString().trim().isNotEmpty()) {
//                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
//                for (listener in listeners) {
//                    listener.popUpMenuClick2(it)
//                }
//                true
//            } else false
//            returnValue
//        }
//    }
//}