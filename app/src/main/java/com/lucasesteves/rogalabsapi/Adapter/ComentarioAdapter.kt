package com.lucasesteves.rogalabsapi.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucasesteves.rogalabsapi.Model.Comentario
import com.lucasesteves.rogalabsapi.R
import com.lucasesteves.rogalabsapi.databinding.ComentarioBinding

class ComentarioAdapter(
    private val comentarioList: List<Comentario>
) : RecyclerView.Adapter<ComentarioAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ComentarioBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(comentarioList[position])
    }

    override fun getItemCount() = comentarioList.size

    class ViewHolder(
        val binding: ComentarioBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            Comentario: Comentario
        ) {
            binding.nameAPI.text = Comentario.name
            binding.emailAPI.text = Comentario.email
            binding.descricaoAPI.text = Comentario.body
            binding.fotoPerfil.setImageResource(R.drawable.perfil)
        }
    }
}
