package com.felipecoronado.postsapp_zemoga.ui.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.felipecoronado.postsapp_zemoga.R

class FavoritePostsAdapter: RecyclerView.Adapter<FavoritePostsAdapter.FavoritePostsViewHolder>() {

    class FavoritePostsViewHolder(postView: View) : RecyclerView.ViewHolder(postView) {
        val postView: TextView = postView.findViewById(R.id.postTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritePostsViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_all_posts, parent, false)
        return FavoritePostsViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritePostsViewHolder, position: Int) {
        val postList = listOf("firstPost", "SecondPost", "thirdPost")
        for (post in postList) {
            holder.postView.text = post
        }

    }

    override fun getItemCount() = 10

}

