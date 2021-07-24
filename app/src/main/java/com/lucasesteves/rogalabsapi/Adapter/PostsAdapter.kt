package com.lucasesteves.rogalabsapi.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucasesteves.rogalabsapi.Model.Posts
import com.lucasesteves.rogalabsapi.databinding.PostsBinding

class PostsAdapter(
    private val postsList: List<Posts>
) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PostsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postsList[position])
    }

    override fun getItemCount() = postsList.size

    class ViewHolder(
        val binding: PostsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            Posts: Posts
        ) {
            binding.useridAPI.text = Posts.userId.toString()
            binding.idAPI.text = Posts.id.toString()
            binding.titleAPI.text = Posts.title
            binding.bodyAPI.text = Posts.body

        }
    }
}

