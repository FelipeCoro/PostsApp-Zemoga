package com.felipecoronado.postsapp_zemoga.ui.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.felipecoronado.postsapp_zemoga.R
import com.felipecoronado.postsapp_zemoga.data.database.room.models.FavoritePosts
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse


class AllPostsAdapter(
    private val allPostList: MutableList<PostsResponse>,
    private val clickListener: OnItemClickListener
) : RecyclerView.Adapter<AllPostsAdapter.AllPostsViewHolder>() {

    class AllPostsViewHolder(postView: View) : RecyclerView.ViewHolder(postView) {
        val postView: TextView = postView.findViewById(R.id.commentTextView)
        val deleteButton:ImageView = postView.findViewById(R.id.deletePostButton)
        val favoritePost:ImageView = postView.findViewById(R.id.favoritePostImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllPostsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.adapter_all_posts, parent, false)
        return AllPostsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllPostsViewHolder, position: Int) {
        val post = allPostList[position]
        holder.postView.text = post.title
        holder.postView.setOnClickListener { clickListener.navigate(post.id,post.userId) }
        holder.deleteButton.setOnClickListener { deletePost(position) }
        if(post.favorite){
            holder.favoritePost.visibility= View.VISIBLE
        }
        else{holder.favoritePost.visibility = View.GONE}

    }

    override fun getItemCount() = allPostList.size

    interface OnItemClickListener {
        fun navigate(postId: Int, userId:Int)
    }

    private fun deletePost(position: Int)
    {
        allPostList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun clearAllPosts() {
        val size: Int = allPostList.size
        if (size > 0) {
            for (post in allPostList) {
                allPostList.remove(post)
            }
            notifyItemRangeRemoved(0, size)
        }
    }
}
