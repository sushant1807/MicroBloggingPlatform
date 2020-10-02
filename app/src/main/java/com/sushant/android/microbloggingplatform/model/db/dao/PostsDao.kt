package com.sushant.android.microbloggingplatform.model.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sushant.android.microbloggingplatform.model.service.response.Post

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg post: Post)

    @Query("DELETE FROM posts")
    fun deleteAll()

    @Query("SELECT * from posts")
    fun getAllPosts() :MutableList<Post>

    @Query("SELECT * from posts where authorId==:authorID")
    fun getAllPostsByAuthorID(authorID:Int):MutableList<Post>

}