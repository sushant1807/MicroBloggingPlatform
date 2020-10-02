package com.sushant.android.microbloggingplatform.ui.postList

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.squareup.picasso.Picasso
import com.sushant.android.microbloggingplatform.R
import com.sushant.android.microbloggingplatform.databinding.ActivityPostListBinding
import com.sushant.android.microbloggingplatform.model.service.response.Author
import com.sushant.android.microbloggingplatform.model.service.response.Post
import com.sushant.android.microbloggingplatform.ui.base.BaseActivity
import com.sushant.android.microbloggingplatform.ui.postDetail.PostDetailActivity
import com.sushant.android.microbloggingplatform.util.Constants
import com.sushant.android.microbloggingplatform.util.NetworkUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_post_list.*

class PostListActivity : BaseActivity<PostListViewModel,
        ActivityPostListBinding>(PostListViewModel::class.java), SwipeRefreshLayout.OnRefreshListener {

    lateinit var receivedItem: Author

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun initViewModel(viewModel: PostListViewModel) {
        binding.viewModel = viewModel
    }

    override fun getLayoutRes(): Int = R.layout.activity_post_list

    override fun onRefresh() {

    }

    private val mSwipeRefreshLayout: SwipeRefreshLayout by lazy {
        findViewById(R.id.swipeRefresh) as SwipeRefreshLayout
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun init()
    {
        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)

        receivedItem = (intent.getSerializableExtra(Constants.AUTHOR_ID) as? Author)!!
        author_id= receivedItem.id

        user_name.setText(receivedItem.name)
        email_text.setText(receivedItem.email)

        Picasso.get()
            .load(receivedItem.avatarUrl)
            .apply {
                fit()
                centerCrop()
                placeholder(R.drawable.ic_launcher_foreground)
                into(image_user)
            }


        bindLocalePost()
        getRemotePostAndSave(receivedItem.id)
        mSwipeRefreshLayout.setOnRefreshListener(this)
    }

    fun bindLocalePost() {
        if (viewModel.getLocalePosts(author_id).size > 0) {
            bindToList(viewModel.getLocalePosts(author_id))
            Log.e("Error", "bindLocalePost " +viewModel.getLocalePosts(author_id).toString())
        }
    }

    @SuppressLint("WrongConstant")
    fun bindToList(postList: List<Post>) {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_post_list)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val recyclerViewAdapter =
            PostListAdapter(postList, object : PostListAdapter.OnItemClickListener {
                override fun onLongClick(postItem: Post, position: Int): Boolean {
                    return true
                }
                override fun onClick(postItem: Post) {
                    val intent = Intent(this@PostListActivity, PostDetailActivity::class.java)
                    intent.putExtra("POST", postItem)
                    intent.putExtra("AUTHOR", receivedItem)
                    startActivity(intent)
                }
            })
        recyclerView.adapter = recyclerViewAdapter
    }

    fun getRemotePostAndSave(authordID : Int) {

        if (NetworkUtils.isConnectedToInternet(applicationContext)) {
            showLoading()
            val subscribe = serviceClient.getPostsByAuthorID(authordID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        viewModel.addPostResponseToLocale(author_id,result)
                        Log.e("Error", "getRemotePostAndSave $result")
                        hideLoading()
                        bindLocalePost()
                        bindToList(result)
                    },
                    { error ->
                        hideLoading()
                    }
                )
        }
    }

}
