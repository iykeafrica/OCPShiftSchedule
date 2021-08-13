package com.i_africa.shiftcalenderobajana.common

import com.i_africa.shiftcalenderobajana.screens.common.MyPopUpMenu
import com.i_africa.shiftcalenderobajana.screens.common.ScreensNavigator
import com.i_africa.shiftcalenderobajana.screens.viewmvcfactory.ViewMvcFactory
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import java.lang.reflect.Field

class Injector(private val component: PresentationComponent) {

    fun inject(client: Any) {
        for (field in getAllFields(client)) {
            if (isAnnotatedForInjection(field)) {
                injectField(client, field)
            }
        }
    }

    private fun getAllFields(client: Any): Array<out Field> {
        val clientClass = client::class.java
        return clientClass.declaredFields
    }

    private fun isAnnotatedForInjection(field: Field): Boolean {
        val fieldAnnotations = field.annotations
        for (annotation in fieldAnnotations) {
            if (annotation is Service) {
                return true
            }
        }
        return false
    }

    private fun injectField(client: Any, field: Field) {
        val isAccessibleInitially = field.isAccessible
        field.isAccessible = true
        field.set(client, getServiceForClass(field.type))
        field.isAccessible = isAccessibleInitially
    }

    private fun getServiceForClass(type: Class<*>): Any {
        when (type) {
            ScreensNavigator::class.java -> {
                return component.screensNavigator()
            }
            MyPopUpMenu::class.java -> {
                return component.popUpMenu()
            }
            ViewMvcFactory::class.java -> {
                return component.viewMvcFactory()
            }
            MySharedPreferences::class.java -> {
                return component.mySharedPreferences()
            }
            else -> {
                throw Exception("unsupported service type: $type")
            }
        }
    }
}