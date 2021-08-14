package com.i_africa.shiftcalenderobajana

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.i_africa.shiftcalenderobajana.databinding.ActivityMainBinding
import com.i_africa.shiftcalenderobajana.utils.Constant.E_CHANGE_SUBSCRIPTION
import com.i_africa.shiftcalenderobajana.utils.Constant.FCM_DEFAULT_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.FCM_DEFAULT_VALUE
import com.i_africa.shiftcalenderobajana.utils.Constant.MY_PREF
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mSharedPreferences: SharedPreferences
    private lateinit var mEditor: SharedPreferences.Editor
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        val bundle = intent.extras
        if (bundle != null) { //bundle must contain all info sent in "data" field of the notification

            if (bundle.getString("Hello") == null){
                binding.textViewNotification.text = bundle.getString("body")
                Toast.makeText(this, "${bundle.getString("title")}", Toast.LENGTH_LONG).show()
            }

            if (bundle.getString("body") == null) {
                binding.textViewNotification.text = bundle.getString("Hello")
                Toast.makeText(this, "${bundle.getString("Hi")}", Toast.LENGTH_LONG).show()
            }



        }
    }

    private fun init() {
        FirebaseApp.initializeApp(this)
        subscribeUserToEChange()
        getFirebaseUserToken()
        mSharedPreferences = applicationContext.getSharedPreferences(MY_PREF, 0)
        mEditor = mSharedPreferences.edit()
        Log.d(
            TAG,
            "init: Token in shared preference value is: " + mSharedPreferences.getString(
                FCM_DEFAULT_KEY,
                FCM_DEFAULT_VALUE
            )
        )
    }

    private fun subscribeUserToEChange() {
        FirebaseMessaging.getInstance().subscribeToTopic(E_CHANGE_SUBSCRIPTION)
            .addOnCompleteListener { task ->
                var msg = "Subscribed"
                if (!task.isSuccessful) {
                    msg = "Subscription Failed"
                }
                Log.d(TAG, msg)
            }
    }

    private fun getFirebaseUserToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            mEditor.putString(FCM_DEFAULT_KEY, token)
            mEditor.commit()

            // Log and toast
            val msg = "This is your token$token"
            Log.d(TAG, "token: $msg")
        })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
