package com.felipecoronado.postsapp_zemoga.ui.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.felipecoronado.postsapp_zemoga.R
import com.felipecoronado.postsapp_zemoga.data.webservice.PostsResponse

class AllPostsAdapter (private val postList: List<PostsResponse> ): RecyclerView.Adapter<AllPostsAdapter.AllPostsViewHolder>() {

    class AllPostsViewHolder(postView: View) : RecyclerView.ViewHolder(postView) {
        val postView: TextView = postView.findViewById(R.id.postTextView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllPostsViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_all_posts, parent, false)
        return AllPostsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllPostsViewHolder, position: Int) {
        val post = postList[position]
            holder.postView.text = post.title
        }

    override fun getItemCount() = postList.size

}
