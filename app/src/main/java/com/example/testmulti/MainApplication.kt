package com.example.testmulti

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    super.log(priority, "global_tag_$tag", message, t)
                }

                override fun createStackElementTag(element: StackTraceElement) =
                    "${element.methodName}: ${super.createStackElementTag(element)}"
            })
            Timber.d("App Created")
        }
    }
}