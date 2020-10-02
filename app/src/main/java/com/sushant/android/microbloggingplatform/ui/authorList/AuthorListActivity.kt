package com.sushant.android.microbloggingplatform.ui.authorList

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sushant.android.microbloggingplatform.R
import com.sushant.android.microbloggingplatform.databinding.ActivityAuthorListBinding
import com.sushant.android.microbloggingplatform.model.service.response.Author
import com.sushant.android.microbloggingplatform.ui.base.BaseActivity
import com.sushant.android.microbloggingplatform.ui.postList.PostListActivity
import com.sushant.android.microbloggingplatform.util.Constants
import com.sushant.android.microbloggingplatform.util.NetworkUtils
import dagger.android.DispatchingAndroidInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthorListActivity : BaseActivity<AuthorViewModel, ActivityAuthorListBinding>(AuthorViewModel::class.java),
    SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun getLayoutRes(): Int = R.layout.activity_author_list

    override fun initViewModel(viewModel: AuthorViewModel) {
        binding.viewModel = viewModel
    }

    private val mSwipeRefreshLayout: SwipeRefreshLayout by lazy {
        findViewById(R.id.swipeRefresh) as SwipeRefreshLayout
    }

    override fun onRefresh() {
        getRemoteAuthorsAndSave()
        mSwipeRefreshLayout.isRefreshing = false
    }

    fun init() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        bindLocaleAuhtors()
        getRemoteAuthorsAndSave()
        mSwipeRefreshLayout.setOnRefreshListener(this)
    }

    @SuppressLint("CheckResult")
    fun getRemoteAuthorsAndSave() {
        if (NetworkUtils.isConnectedToInternet(applicationContext)) {
            showLoading()
            serviceClient.getAuthorsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        viewModel.addAuthorsResponseToLocale(result)
                        hideLoading()
                        bindLocaleAuhtors()
                        bindToList(result)
                    },
                    {
                        hideLoading()
                    }
                )
        }
    }

    fun bindLocaleAuhtors() {
        if (viewModel.getLocaleAuthors().size > 0) {
            bindToList(viewModel.getLocaleAuthors())
        }
    }

    @SuppressLint("WrongConstant")
    fun bindToList(authorList: List<Author>) {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_author_list)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val recyclerViewAdapter =
            AuthorListAdapter(authorList, object : AuthorListAdapter.OnItemClickListener {
                override fun onLongClick(authorItem: Author, position: Int): Boolean {
                    return true
                }

                override fun onClick(authorItem: Author) {
                    val intent = Intent(this@AuthorListActivity, PostListActivity::class.java)
                    intent.putExtra(Constants.AUTHOR_ID, authorItem)
                    startActivity(intent)
                }
            })
        recyclerView.adapter = recyclerViewAdapter
    }

}
