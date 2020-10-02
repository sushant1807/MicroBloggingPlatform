package com.sushant.android.microbloggingplatform.model.service.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "posts")
data class Post(

    @PrimaryKey @ColumnInfo @SerializedName("id")
    var id: Int? = null,
    @ColumnInfo @SerializedName("date")
    var date: String? = null,
    @ColumnInfo @SerializedName("title")
    var title: String? = null,
    @ColumnInfo @SerializedName("body")
    var body: String? = null,
    @ColumnInfo @SerializedName("imageUrl")
    var imageUrl: String? = null,
    @ColumnInfo @SerializedName("authorId")
    var authorId: Int? = null
) : Serializable