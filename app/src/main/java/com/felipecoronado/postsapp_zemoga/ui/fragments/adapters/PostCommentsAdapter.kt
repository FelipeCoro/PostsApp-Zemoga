package com.felipecoronado.postsapp_zemoga.ui.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.felipecoronado.postsapp_zemoga.R
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.CommentsResponse

class PostCommentsAdapter(private val commentsList: List<CommentsResponse>
) : RecyclerView.Adapter<PostCommentsAdapter.PostCommentsViewHolder>() {

    class PostCommentsViewHolder(commentView: View) : RecyclerView.ViewHolder(commentView) {
        val postView: TextView = commentView.findViewById(R.id.commentTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostCommentsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.adapter_post_description, parent, false
        )
        return PostCommentsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostCommentsViewHolder, position: Int) {
        val comment = commentsList[position]
        holder.postView.text = comment.body


    }

    override fun getItemCount() = commentsList.size

}

