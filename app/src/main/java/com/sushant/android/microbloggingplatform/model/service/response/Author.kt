package com.sushant.android.microbloggingplatform.model.service.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "authors")
data class Author(
        @PrimaryKey @ColumnInfo @SerializedName("id")
        var id: Int,
        @ColumnInfo @SerializedName("name")
        var name: String,
        @ColumnInfo @SerializedName("userName")
        var userName: String,
        @ColumnInfo @SerializedName("email")
        var email: String,
        @ColumnInfo @SerializedName("avatarUrl")
        var avatarUrl: String

) : Serializable
