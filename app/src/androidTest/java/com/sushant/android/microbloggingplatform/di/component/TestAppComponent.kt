package com.sushant.android.microbloggingplatform.di.component

import com.sushant.android.microbloggingplatform.App
import com.sushant.android.microbloggingplatform.di.module.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    TestAppModule::class,
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilderModule::class,
    ViewModelModule::class,
    NetworkModule::class,
    DatabaseModule::class])
interface TestAppComponent : AppComponent {


    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<App>


}

