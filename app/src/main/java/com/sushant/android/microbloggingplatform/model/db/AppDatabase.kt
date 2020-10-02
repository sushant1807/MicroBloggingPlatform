package com.sushant.android.microbloggingplatform.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sushant.android.microbloggingplatform.model.db.dao.AuthorDao
import com.sushant.android.microbloggingplatform.model.db.dao.PostsDao
import com.sushant.android.microbloggingplatform.model.service.response.Author
import com.sushant.android.microbloggingplatform.model.service.response.Post

@Database(entities = arrayOf(Author::class, Post::class), version = 2, exportSchema = false)

//@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun authorDao(): AuthorDao
    abstract fun postDao(): PostsDao
}