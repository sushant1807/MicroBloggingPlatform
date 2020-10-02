package com.sushant.android.microbloggingplatform.ui.postList

import com.sushant.android.microbloggingplatform.App
import com.sushant.android.microbloggingplatform.model.db.AppDatabase
import com.sushant.android.microbloggingplatform.model.service.response.Post
import com.sushant.android.microbloggingplatform.ui.base.BaseViewModel
import javax.inject.Inject

class PostListViewModel @Inject internal constructor(val app: App, private val db: AppDatabase) : BaseViewModel() {

    fun addPostResponseToLocale(authorId:Int, postResult: List<Post>) {

        if(postResult.size>0)
        {
            db.postDao().deleteAll()
            postResult.forEach { postItem ->
                db.postDao().insert(postItem)
            }
        }
    }

    private var postLocaleData: MutableList<Post>? = null

    fun getLocalePosts(authorId:Int): List<Post> {
        if (postLocaleData == null) {
            postLocaleData = mutableListOf<Post>()
            loadLocalePost(authorId)
        }
        return postLocaleData!!;
    }

    fun loadLocalePost(authorID:Int) {

        postLocaleData = db.postDao().getAllPostsByAuthorID(authorID)
    }
}