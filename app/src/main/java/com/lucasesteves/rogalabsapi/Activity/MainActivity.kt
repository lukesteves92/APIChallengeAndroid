package com.lucasesteves.rogalabsapi.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    val retrofitClient = RetrofitInstance
        .getRetrofitInstance("https://jsonplaceholder.typicode.com")
    val endpoint = retrofitClient.create(Endpoint::class.java)
    val callback = endpoint.getPosts()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fun showRecycleView(Posts: List<Posts>) {
            val postAdapter = PostsAdapter(Posts) {
                Toast.makeText(
                    this@MainActivity,
                    "${it.title} - ${it.body}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            with(binding) {
                postsRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                postsRecyclerView.adapter = postAdapter

            }
        }

        callback.enqueue(object : Callback<List<Posts>> {
            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                response.body()?.forEach { body ->
                    showRecycleView(listOf(Posts(body.userId, body.id, body.title, body.body)))
                }
            }
        })

    }


}
