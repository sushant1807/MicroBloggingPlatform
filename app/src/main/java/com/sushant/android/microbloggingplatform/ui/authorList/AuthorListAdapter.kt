package com.sushant.android.microbloggingplatform.ui.authorList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sushant.android.microbloggingplatform.R
import com.sushant.android.microbloggingplatform.model.service.response.Author

class AuthorListAdapter(val authorList: List<Author>, val listener: OnItemClickListener):
    RecyclerView.Adapter<AuthorListAdapter.AuthorViewHolder>() {

    interface OnItemClickListener {
        fun onClick(authorItem: Author)
        fun onLongClick(authorItem: Author,position: Int): Boolean
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {

        holder.nameText?.text = authorList.get(position).name
        holder.emailText?.text = authorList.get(position).email
        holder.click(authorList.get(position),listener,position)

        Picasso.get()
            .load(authorList.get(position).avatarUrl)
            .apply {
                fit()
                centerCrop()
                placeholder(R.drawable.ic_launcher_foreground)
                into(holder.authorImage)
            }
    }

    override fun onCreateViewHolder(parentView: ViewGroup, position: Int): AuthorViewHolder {
        val view = LayoutInflater.from(parentView.context).inflate(R.layout.list_item_author,
            parentView, false)
        return AuthorViewHolder(view);
    }

    override fun getItemCount(): Int {
        return authorList.size
    }

    class AuthorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val nameText = itemView.findViewById<TextView>(R.id.user_name_text)
        val emailText = itemView.findViewById<TextView>(R.id.email_text)
        val authorImage = itemView.findViewById<ImageView>(R.id.image_avatar)


        fun click(authorItem: Author, listener: OnItemClickListener, position: Int)
        {
            itemView.setOnClickListener {
                listener.onClick(authorItem)
            }

            itemView.setOnLongClickListener {
                listener.onLongClick(authorItem,position)
            }
        }
    }

}