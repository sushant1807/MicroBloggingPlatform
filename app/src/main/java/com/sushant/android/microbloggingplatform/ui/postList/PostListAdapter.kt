package com.sushant.android.microbloggingplatform.ui.postList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sushant.android.microbloggingplatform.R
import com.sushant.android.microbloggingplatform.model.service.response.Post
import com.sushant.android.microbloggingplatform.util.Utils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class PostListAdapter (val postList: List<Post>, val listener: OnItemClickListener):
    RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {

    interface OnItemClickListener {
        fun onClick(postItem: Post)
        fun onLongClick(postItem: Post,position: Int): Boolean
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        holder.nameText?.text = postList[position].title
        holder.emailText?.text = Utils.generateDateDesc(postList.get(position).date.toString())

        Picasso.get()
            .load(postList[position].imageUrl)
            .apply {
                fit()
                centerCrop()
                placeholder(R.drawable.ic_launcher_foreground)
                into(holder.authorImage)
            }

        holder.click(postList.get(position),listener,position)

    }

    override fun onCreateViewHolder(parentView: ViewGroup, position: Int): PostViewHolder {
        val view = LayoutInflater.from(parentView.context).inflate(R.layout.list_item_author, parentView, false)
        return PostViewHolder(view);
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val nameText = itemView.findViewById<TextView>(R.id.user_name_text)
        val emailText = itemView.findViewById<TextView>(R.id.email_text)
        val authorImage = itemView.findViewById<ImageView>(R.id.image_avatar)

        fun click(postItem: Post,listener: OnItemClickListener, position: Int)
        {
            itemView.setOnClickListener {
                listener.onClick(postItem)
            }

            itemView.setOnLongClickListener {
                listener.onLongClick(postItem,position)
            }
        }
    }
}