package com.i_africa.shiftcalenderobajana.common.di.firebase_messaging_service

import com.google.firebase.messaging.FirebaseMessagingService
import dagger.Module
import dagger.Provides

@Module
class FirebaseMessageService(val firebaseMessagingService: FirebaseMessagingService) {

    @Provides
    fun firebaseMessagingService() : FirebaseMessagingService = firebaseMessagingService
}