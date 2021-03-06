package com.i_africa.shiftcalenderobajana.common.di.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.i_africa.shiftcalenderobajana.screens.common.MyPopUpMenu
import com.i_africa.shiftcalenderobajana.screens.common.ScreensNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    fun layoutInflater(activity: Activity) = activity.layoutInflater

    @Provides
    fun appCompatActivity(activity: Activity) = activity as AppCompatActivity

    @ActivityScoped
    @Provides
    fun screensNavigator(appCompatActivity: AppCompatActivity)  = ScreensNavigator(appCompatActivity)

    @Provides
    fun popUpMenu(appCompatActivity: AppCompatActivity) = MyPopUpMenu(appCompatActivity)

}