package com.kedirilagi.astro.data.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kedirilagi.astro.R
import com.kedirilagi.astro.databinding.AdapterRiwayatAktivitasBinding
import com.kedirilagi.astro.network.response.RiwayatAktivitasResponse

class RiwayatAktivitasAdapter(
    var aktivitass: ArrayList<RiwayatAktivitasResponse>
) : RecyclerView.Adapter<RiwayatAktivitasAdapter.ViewHolder>() {


    class ViewHolder(val binding: AdapterRiwayatAktivitasBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterRiwayatAktivitasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aktivitas = aktivitass[position]
        holder.binding.tvAktivitas.text = aktivitas.nama_akvitias
        holder.binding.tvJam.text = aktivitas.jam

        val image = Glide.with(holder.binding.ivAktivitas.context)
        if (aktivitas.nama_akvitias == "Makan") {
            image.load(R.drawable.ic_makan)
                .centerCrop()
                .into(holder.binding.ivAktivitas)
        } else if (aktivitas.nama_akvitias == "Minum") {
            image.load(R.drawable.ic_minum)
                .centerCrop()
                .into(holder.binding.ivAktivitas)
        } else if (aktivitas.nama_akvitias == "Pup") {
            image.load(R.drawable.ic_pup)
                .centerCrop()
                .into(holder.binding.ivAktivitas)
        }

    }

    override fun getItemCount() = aktivitass.size

    fun setData(data: List<RiwayatAktivitasResponse>) {
        aktivitass.clear()
        aktivitass.addAll(data)
        notifyDataSetChanged()
    }

}