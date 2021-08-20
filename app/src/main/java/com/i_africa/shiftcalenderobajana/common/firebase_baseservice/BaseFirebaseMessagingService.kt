package com.i_africa.shiftcalenderobajana.common.firebase_baseservice

import android.annotation.SuppressLint
import com.google.firebase.messaging.FirebaseMessagingService
import com.i_africa.shiftcalenderobajana.MyApplication
import com.i_africa.shiftcalenderobajana.common.di.firebase.FirebaseMessagingComponent

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
open class BaseFirebaseMessagingService: FirebaseMessagingService() {
    private val appComponent get() = (application as MyApplication).appComponent

    private val firebaseMessagingComponent: FirebaseMessagingComponent by lazy {
        appComponent.newFirebaseMessagingComponent()
    }

    val injector get() = firebaseMessagingComponent
}