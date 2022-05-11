package com.felipecoronado.postsapp_zemoga.ui.fragments.adapters

import android.R.attr.data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.felipecoronado.postsapp_zemoga.R
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse


class AllPostsAdapter(
    private val postList: MutableList<PostsResponse>,
    private val clickListener: OnItemClickListener
) : RecyclerView.Adapter<AllPostsAdapter.AllPostsViewHolder>() {

    class AllPostsViewHolder(postView: View) : RecyclerView.ViewHolder(postView) {
        val postView: TextView = postView.findViewById(R.id.commentTextView)
        val deleteButton:ImageView = postView.findViewById(R.id.deletePostButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllPostsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.adapter_all_posts, parent, false)
        return AllPostsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllPostsViewHolder, position: Int) {
        val post = postList[position]
        holder.postView.text = post.title
        holder.postView.setOnClickListener { clickListener.navigate(post.id,post.userId) }
        holder.deleteButton.setOnClickListener { deletePost(position) }

    }

    override fun getItemCount() = postList.size

    interface OnItemClickListener {
        fun navigate(postId: Int, userId:Int)
    }

    private fun deletePost(position: Int)
    {
        postList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun clearAllPosts() {
        val size: Int = postList.size
        if (size > 0) {
            for (post in postList) {
                postList.remove(post)
            }
            notifyItemRangeRemoved(0, size)
        }
    }
}
