package com.sushant.android.microbloggingplatform.ui.authorList

import com.sushant.android.microbloggingplatform.App
import com.sushant.android.microbloggingplatform.model.db.AppDatabase
import com.sushant.android.microbloggingplatform.model.service.response.Author
import com.sushant.android.microbloggingplatform.ui.base.BaseViewModel
import javax.inject.Inject

class AuthorViewModel @Inject internal constructor(val app: App, private val db: AppDatabase) : BaseViewModel() {

    init {
        loadLocaleAuthors()
    }

    fun addAuthorsResponseToLocale(authorsRemoteResult: List<Author>) {

        if(authorsRemoteResult.isNotEmpty()) {
            db.authorDao().deleteAll()
            authorsRemoteResult.forEach { author ->
                db.authorDao().insert(author)
            }
        }
    }


    private var authorsLocaleData: MutableList<Author>? = null

    fun getLocaleAuthors(): List<Author> {
        if (authorsLocaleData == null) {
            authorsLocaleData = mutableListOf<Author>()
            loadLocaleAuthors()
        }
        return authorsLocaleData!!;
    }

    private fun loadLocaleAuthors() {
        authorsLocaleData = db.authorDao().getAllAuthors()
    }
}



