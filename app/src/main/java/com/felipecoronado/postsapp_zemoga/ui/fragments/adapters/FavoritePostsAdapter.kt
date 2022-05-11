package com.felipecoronado.postsapp_zemoga.ui.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.felipecoronado.postsapp_zemoga.R
import com.felipecoronado.postsapp_zemoga.data.database.room.models.FavoritePosts

class FavoritePostsAdapter(
    private val postList: List<FavoritePosts>,
    private val clickListener: OnItemClickListener
) : RecyclerView.Adapter<FavoritePostsAdapter.FavoritePostsViewHolder>() {

    class FavoritePostsViewHolder(postView: View) : RecyclerView.ViewHolder(postView) {
        val postView: TextView = postView.findViewById(R.id.commentTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritePostsViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_favorite_posts, parent, false)
        return FavoritePostsViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritePostsViewHolder, position: Int) {
        val post = postList[position]
        holder.postView.text = post.title
        holder.postView.setOnClickListener { clickListener.navigate(post.id, post.userId) }
        
    }

    override fun getItemCount() = postList.size

    interface OnItemClickListener {
        fun navigate(postId: Int, userId: Int)
    }

}

