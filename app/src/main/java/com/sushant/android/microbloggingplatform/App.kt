package com.sushant.android.microbloggingplatform

import android.app.Activity
import com.sushant.android.microbloggingplatform.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerActivity
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

open class App : DaggerApplication() {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    fun activityInjector() = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)

    }

}