package com.i_africa.shiftcalenderobajana.common.di.app

import com.i_africa.shiftcalenderobajana.common.di.activity.ActivityComponent
import com.i_africa.shiftcalenderobajana.common.di.activity.ActivityModule
import com.i_africa.shiftcalenderobajana.common.di.firebase_messaging_service.FirebaseMessagingComponent
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun newActivityComponent(activityModule: ActivityModule) : ActivityComponent

    fun newFirebaseMessagingComponent() : FirebaseMessagingComponent
}