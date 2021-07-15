package com.kedirilagi.astro.data.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kedirilagi.astro.databinding.AdapterRiwayatJatuhBinding
import com.kedirilagi.astro.network.response.StatusPasienResponse

class RiwayatJatuhAdapter(
    var kondisis: ArrayList<StatusPasienResponse>
) : RecyclerView.Adapter<RiwayatJatuhAdapter.ViewHolder>() {


    class ViewHolder(val binding: AdapterRiwayatJatuhBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterRiwayatJatuhBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val kondisi = kondisis[position]
        holder.binding.tvAktivitas.text = kondisi.kondisi
        val regexJam = kondisi.jam.toString().replace("-", ":")
        holder.binding.tvJam.text = regexJam

        holder.binding.tvTanggal.text = kondisi.tanggal


    }

    override fun getItemCount() = kondisis.size

    fun setData(data: List<StatusPasienResponse>) {
        kondisis.clear()
        kondisis.addAll(data)
        notifyDataSetChanged()
    }

}