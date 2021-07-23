package com.lucasesteves.rogalabsapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucasesteves.rogalabsapi.model.posts
import com.lucasesteves.rogalabsapi.databinding.PostsBinding
import retrofit2.Response

class PostsAdapter(
    private val postsList: List<posts>,
    private val onClickListener: (posts: posts) -> Unit
) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PostsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postsList[position], onClickListener)
    }

    override fun getItemCount() = postsList.size

    class ViewHolder(
        val binding: PostsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            posts: Response<List<posts>>,
            onClickListener: (posts: posts) -> Unit
        ) {
            binding.titleAPI.text = posts.title
            binding.bodyAPI.text = posts.body
            binding.postsContainer.setOnClickListener {
                onClickListener(posts)
            }

        }
    }
}

