package com.sushant.android.microbloggingplatform.di.module

import com.sushant.android.microbloggingplatform.di.scope.ActivityScope
import com.sushant.android.microbloggingplatform.model.service.ApiService
import com.sushant.android.microbloggingplatform.ui.postList.PostListActivity
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class PostActivityModule {

    @ActivityScope
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun provideActivityName(postListActivity: PostListActivity): String {
        return postListActivity.javaClass.name
    }
}
