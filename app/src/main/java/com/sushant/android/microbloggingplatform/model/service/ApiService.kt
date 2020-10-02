package com.sushant.android.microbloggingplatform.model.service

import com.sushant.android.microbloggingplatform.model.service.response.Author
import com.sushant.android.microbloggingplatform.model.service.response.Post
import com.sushant.android.microbloggingplatform.util.Constants
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("authors")
    fun getAuthorsList(): Single<List<Author>>

    @GET("posts")
    fun getPostsByAuthorID(
        @Query("authorId") page: Int = 1
    ) : Single<List<Post>>

    companion object {

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.API_BASE_URL)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}