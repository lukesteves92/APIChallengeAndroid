package com.lucasesteves.rogalabsapi.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucasesteves.rogalabsapi.Adapter.ComentarioAdapter
import com.lucasesteves.rogalabsapi.Adapter.PostsAdapter
import com.lucasesteves.rogalabsapi.Endpoint.EndpointComents
import com.lucasesteves.rogalabsapi.Model.Comentario
import com.lucasesteves.rogalabsapi.Model.Posts
import com.lucasesteves.rogalabsapi.R
import com.lucasesteves.rogalabsapi.Utils.RetrofitInstance
import com.lucasesteves.rogalabsapi.databinding.ActivityComentarioBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class ComentarioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComentarioBinding
    private val retrofitClient = RetrofitInstance
        .getRetrofitInstance("https://jsonplaceholder.typicode.com")
    private val endpoint = retrofitClient.create(EndpointComents::class.java)
    private val callback = endpoint.getComents()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComentarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra(KEY_TITLE)
        val id = intent.getIntExtra(KEY_POSTID, 0)

        binding.topAppBar2.title = title

        callback.enqueue(object : Callback<List<Comentario>> {
            override fun onFailure(call: Call<List<Comentario>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Comentario>>, response: Response<List<Comentario>>) {
                showDataComents(response.body()!!)
            }
        })
    }

    private fun showDataComents(comentarioList: List<Comentario>) {
        comentarioList.forEach { it ->
            val comentarioAdapter = ComentarioAdapter(comentarioList)
            with(binding) {
                comentarioRecyclerView.layoutManager = LinearLayoutManager(this@ComentarioActivity)
                comentarioRecyclerView.adapter = comentarioAdapter
            }

        }

    }

    companion object {
        const val KEY_TITLE = "título"
        const val KEY_POSTID = "id"
    }

}