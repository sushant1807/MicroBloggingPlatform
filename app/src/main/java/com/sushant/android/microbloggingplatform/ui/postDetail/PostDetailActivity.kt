package com.sushant.android.microbloggingplatform.ui.postDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import com.sushant.android.microbloggingplatform.R
import com.sushant.android.microbloggingplatform.model.service.response.Author
import com.sushant.android.microbloggingplatform.model.service.response.Post
import com.sushant.android.microbloggingplatform.util.Utils
import kotlinx.android.synthetic.main.activity_post_detail.*

class PostDetailActivity : AppCompatActivity() {

    lateinit var receivedPostItem: Post
    lateinit var receivedAuthorItem: Author

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        receivedPostItem = (intent.getSerializableExtra("POST") as? Post)!!
        receivedAuthorItem = (intent.getSerializableExtra("AUTHOR") as? Author)!!

        initInstance()
    }

    private fun initInstance() {

        title_text_view.setText(receivedPostItem.title)
        description_text_view.setText(receivedPostItem.body)
        date_of_creation.setText(Utils.generateDateDesc(receivedPostItem.date.toString()))
        author_name.setText(receivedAuthorItem.name)

        Picasso.get()
            .load(receivedPostItem.imageUrl)
            .apply {
                fit()
                centerCrop()
                placeholder(R.drawable.ic_launcher_foreground)
                into(itemImgView)
            }
    }
}
