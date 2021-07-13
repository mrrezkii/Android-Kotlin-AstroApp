package com.kedirilagi.astro.data.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kedirilagi.astro.databinding.AdapterJadwalBinding
import com.kedirilagi.astro.storage.persistence.JadwalEntity

class JadwalAdapter(
    var jadwals: ArrayList<JadwalEntity>
) : RecyclerView.Adapter<JadwalAdapter.ViewHolder>() {

    class ViewHolder(val binding: AdapterJadwalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterJadwalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val jadwal = jadwals[position]
        holder.binding.tvAktivitas.text = jadwal.aktivitas
        holder.binding.tvAlamat.text = jadwal.aktivitas
        holder.binding.tvTanggal.text = jadwal.tanggal
        holder.binding.tvJam.text = jadwal.jam
    }

    override fun getItemCount() = jadwals.size

    fun setData(data: List<JadwalEntity>) {
        jadwals.clear()
        jadwals.addAll(data)
        notifyDataSetChanged()
    }

}