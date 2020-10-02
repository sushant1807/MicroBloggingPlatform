package com.sushant.android.microbloggingplatform.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.sushant.android.microbloggingplatform.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: App): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideSharedPreferences(app: App): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(app)

}

