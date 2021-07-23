package com.lucasesteves.rogalabsapi.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lucasesteves.rogalabsapi.Endpoint.Endpoint
import com.lucasesteves.rogalabsapi.R
import com.lucasesteves.rogalabsapi.databinding.ActivityMainBinding
import com.lucasesteves.rogalabsapi.model.Posts
import com.lucasesteves.rogalabsapi.utils.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
    }

    fun getData() {
        val retrofitClient = RetrofitInstance
            .getRetrofitInstance("https://jsonplaceholder.typicode.com")

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getPosts()

        callback.enqueue(object : Callback<List<Posts>> {
            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                response.body()?.forEach {
                    binding?.textView?.text = title.toString().plus(it.body)
                }
            }
        })

    }
}