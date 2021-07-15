package com.kedirilagi.astro.data.view.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.kedirilagi.astro.databinding.AdapterRiwayatJatuhBinding
import com.kedirilagi.astro.extension.dayExtension
import com.kedirilagi.astro.extension.monthExtension
import com.kedirilagi.astro.network.response.StatusPasienResponse
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RiwayatJatuhAdapter(
    var kondisis: ArrayList<StatusPasienResponse>
) : RecyclerView.Adapter<RiwayatJatuhAdapter.ViewHolder>() {


    class ViewHolder(val binding: AdapterRiwayatJatuhBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterRiwayatJatuhBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val kondisi = kondisis[position]
        holder.binding.tvAktivitas.text = kondisi.kondisi
        val regexJam = kondisi.jam.toString().replace("-", ":")
        holder.binding.tvJam.text = regexJam

        val tanggal = LocalDate.parse(kondisi.tanggal, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val hari = dayExtension(tanggal.year, tanggal.monthValue - 1, tanggal.dayOfMonth)
        val bulan = monthExtension(tanggal.year, tanggal.monthValue - 1, tanggal.dayOfMonth)
        val date = "$hari, ${tanggal.dayOfMonth} $bulan ${tanggal.year}"

        holder.binding.tvTanggal.text = date


    }

    override fun getItemCount() = kondisis.size

    fun setData(data: List<StatusPasienResponse>) {
        kondisis.clear()
        kondisis.addAll(data)
        notifyDataSetChanged()
    }

}