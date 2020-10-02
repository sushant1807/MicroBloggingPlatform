package com.sushant.android.microbloggingplatform.di.module

import com.sushant.android.microbloggingplatform.di.scope.ActivityScope
import com.sushant.android.microbloggingplatform.ui.authorList.AuthorListActivity
import com.sushant.android.microbloggingplatform.ui.postList.PostListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [AuthorActivityModule::class])
    abstract  fun bindAuthorListActivity(): AuthorListActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [PostActivityModule::class])
    abstract  fun bindPostListActivity():PostListActivity

}