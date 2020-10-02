package com.sushant.android.microbloggingplatform.di.module

import com.sushant.android.microbloggingplatform.di.scope.ActivityScope
import com.sushant.android.microbloggingplatform.model.service.ApiService
import com.sushant.android.microbloggingplatform.ui.authorList.AuthorListActivity
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthorActivityModule {

    @ActivityScope
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun provideActivityName(mainActivity: AuthorListActivity): String {
        return mainActivity.javaClass.name
    }
}
