package com.kedirilagi.astro.data.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kedirilagi.astro.data.model.NotifikasiModel
import com.kedirilagi.astro.databinding.AdapterNotifikasiBinding

class NotifikasiAdapter(
    var notifikasis: ArrayList<NotifikasiModel>,
) : RecyclerView.Adapter<NotifikasiAdapter.ViewHolder>() {

    class ViewHolder(val binding: AdapterNotifikasiBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterNotifikasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notifikasi = notifikasis[position]
        holder.binding.tvTime.text = "${notifikasi.time} hari yang lalu"
        holder.binding.tvContent.text = notifikasi.content
    }

    override fun getItemCount() = notifikasis.size

    fun setData(data: List<NotifikasiModel>) {
        notifikasis.clear()
        notifikasis.addAll(data)
        notifyDataSetChanged()
    }

}