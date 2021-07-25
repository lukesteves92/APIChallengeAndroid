package com.lucasesteves.rogalabsapi.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucasesteves.rogalabsapi.Adapter.PostsAdapter
import com.lucasesteves.rogalabsapi.databinding.ActivityMainBinding
import com.lucasesteves.rogalabsapi.Endpoint.Endpoint
import com.lucasesteves.rogalabsapi.Model.Posts
import com.lucasesteves.rogalabsapi.Utils.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val retrofitClient = RetrofitInstance
        .getRetrofitInstance("https://jsonplaceholder.typicode.com")
    private val endpoint = retrofitClient.create(Endpoint::class.java)
    private val callback = endpoint.getPosts()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        callback.enqueue(object : Callback<List<Posts>> {
            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                showData(response.body()!!)
            }
        })
    }

    private fun showData(postList: List<Posts>) {
        postList.forEach { it ->
            val postAdapter = PostsAdapter(postList) {
                val titleComentario = it.title
                val intent = Intent(this@MainActivity, ComentarioActivity::class.java)
                intent.putExtra(KEY_TITLE, titleComentario)
                startActivity(intent)
            }
            with(binding) {
                postsRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                postsRecyclerView.adapter = postAdapter
            }

        }

    }

    companion object {
        const val KEY_TITLE = "título"
    }


}
