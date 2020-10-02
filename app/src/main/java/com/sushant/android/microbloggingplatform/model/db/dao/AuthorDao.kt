package com.sushant.android.microbloggingplatform.model.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.sushant.android.microbloggingplatform.model.service.response.Author

@Dao
interface AuthorDao {

    @Insert(onConflict = REPLACE)
    fun insert(vararg author: Author)

    @Query("DELETE FROM authors")
    fun deleteAll()

    @Query("SELECT * from authors")
    fun getAllAuthors() :MutableList<Author>

    @Query("SELECT COUNT(*) from authors")
    fun getAllAuthorsCount() :Int

}