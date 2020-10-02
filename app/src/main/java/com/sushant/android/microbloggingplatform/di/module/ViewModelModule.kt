package com.sushant.android.microbloggingplatform.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sushant.android.microbloggingplatform.util.ViewModelFactory
import com.sushant.android.microbloggingplatform.di.key.ViewModelKey
import com.sushant.android.microbloggingplatform.ui.authorList.AuthorViewModel
import com.sushant.android.microbloggingplatform.ui.postList.PostListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AuthorViewModel::class)
    internal abstract fun bindAuthorViewModel(viewModel: AuthorViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostListViewModel::class)
    internal abstract fun bindPostViewModel(viewModel: PostListViewModel): ViewModel

}