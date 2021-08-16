package com.i_africa.shiftcalenderobajana.common.di.firebase_messaging_service

import com.i_africa.shiftcalenderobajana.firebase.MyFirebaseMessagingService
import dagger.Subcomponent

@Subcomponent()
interface FirebaseMessagingComponent {
    fun inject(myFirebaseMessagingService: MyFirebaseMessagingService)
}