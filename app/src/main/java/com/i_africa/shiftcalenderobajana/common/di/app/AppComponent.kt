package com.i_africa.shiftcalenderobajana.common.di.app

import com.i_africa.shiftcalenderobajana.common.di.activity.ActivityComponent
import com.i_africa.shiftcalenderobajana.common.di.activity.ActivityModule
import com.i_africa.shiftcalenderobajana.common.di.firebase.FirebaseMessagingComponent
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun newActivityComponentBuilder() : ActivityComponent.Builder

    fun newFirebaseMessagingComponent() : FirebaseMessagingComponent
}