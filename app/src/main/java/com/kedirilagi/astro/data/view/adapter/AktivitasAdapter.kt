package com.kedirilagi.astro.data.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kedirilagi.astro.R
import com.kedirilagi.astro.data.model.AktivitasModel
import com.kedirilagi.astro.databinding.AdapterAktivitasBinding

class AktivitasAdapter(
    var aktivitass: ArrayList<AktivitasModel>
) : RecyclerView.Adapter<AktivitasAdapter.ViewHolder>() {


    class ViewHolder(val binding: AdapterAktivitasBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterAktivitasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aktivitas = aktivitass[position]
        holder.binding.tvAktivitas.text = aktivitas.aktivitas
        holder.binding.tvJam.text = aktivitas.jam

        val image = Glide.with(holder.binding.ivAktivitas.context)
        if (aktivitas.aktivitas == "Makan") {
            image.load(R.drawable.ic_makan)
                .centerCrop()
                .into(holder.binding.ivAktivitas)
        } else if (aktivitas.aktivitas == "Minum") {
            image.load(R.drawable.ic_minum)
                .centerCrop()
                .into(holder.binding.ivAktivitas)
        } else if (aktivitas.aktivitas == "Pup") {
            image.load(R.drawable.ic_pup)
                .centerCrop()
                .into(holder.binding.ivAktivitas)
        }

    }

    override fun getItemCount() = aktivitass.size

    fun setData(data: List<AktivitasModel>) {
        aktivitass.clear()
        aktivitass.addAll(data)
        notifyDataSetChanged()
    }

}