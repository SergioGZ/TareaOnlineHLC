package com.sergio.apitarea4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class DigimonAdapter(
    private val context: Context,
    private val digimons: List<Digimon>
) : RecyclerView.Adapter<DigimonAdapter.DigimonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DigimonViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_digimon, parent, false)
        return DigimonViewHolder(view)
    }

    override fun onBindViewHolder(holder: DigimonViewHolder, position: Int) {
        val digimon = digimons[position]
        holder.bind(digimon)
    }

    override fun getItemCount(): Int {
        return digimons.size
    }

    inner class DigimonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val digimonImage: ImageView = itemView.findViewById(R.id.digimonImage)
        private val digimonName: TextView = itemView.findViewById(R.id.digimonName)

        fun bind(digimon: Digimon) {
            digimonName.text = digimon.name
            // Utiliza Glide u otra biblioteca para cargar imágenes desde URL
            Glide.with(context)
                .load(digimon.image)
                //.placeholder(R.drawable.placeholder_image) // Imagen de carga mientras se carga desde la URL
                .into(digimonImage)
        }
    }
}