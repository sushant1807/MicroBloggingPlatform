package com.sushant.android.microbloggingplatform.di.module


import androidx.room.Room
import com.sushant.android.microbloggingplatform.model.db.AppDatabase
import com.sushant.android.microbloggingplatform.App
import com.sushant.android.microbloggingplatform.model.db.dao.AuthorDao
import com.sushant.android.microbloggingplatform.model.db.dao.PostsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule{

    @Provides
    @Singleton
    fun provideAppDatabase(app: App): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "blogging_platform.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthorDao(appDatabase: AppDatabase): AuthorDao = appDatabase.authorDao()

    @Provides
    @Singleton
    fun providePostDao(appDatabase: AppDatabase): PostsDao = appDatabase.postDao()
}