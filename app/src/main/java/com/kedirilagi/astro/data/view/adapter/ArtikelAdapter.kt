package com.kedirilagi.astro.data.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kedirilagi.astro.data.model.ArtikelModel
import com.kedirilagi.astro.databinding.AdapterArtikelBinding

class ArtikelAdapter(
    var artikels: ArrayList<ArtikelModel>
) : RecyclerView.Adapter<ArtikelAdapter.ViewHolder>() {


    class ViewHolder(val binding: AdapterArtikelBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterArtikelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artikel = artikels[position]
        holder.binding.tvJudul.text = artikel.judul
        Glide.with(holder.binding.ivArtikel.context)
            .load(artikel.photo)
            .centerCrop()
            .into(holder.binding.ivArtikel)
    }

    override fun getItemCount() = artikels.size

    fun setData(data: List<ArtikelModel>) {
        artikels.clear()
        artikels.addAll(data)
        notifyDataSetChanged()
    }

}