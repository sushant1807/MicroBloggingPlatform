package com.sushant.android.microbloggingplatform.di.module

import android.content.Context
import com.sushant.android.microbloggingplatform.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

class TestAppModule {
}


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: App): Context = app.applicationContext

}