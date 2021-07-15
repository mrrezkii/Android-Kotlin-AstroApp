package com.kedirilagi.astro.data.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kedirilagi.astro.databinding.AdapterLokasiBinding
import com.kedirilagi.astro.network.response.DataRSResponse

class DataRSAdapter(
    var datas: ArrayList<DataRSResponse>
) : RecyclerView.Adapter<DataRSAdapter.ViewHolder>() {

    class ViewHolder(val binding: AdapterLokasiBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterLokasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = datas[position]
        holder.binding.tvAlamat.text = data.alamat
        holder.binding.tvNamaRumahSakit.text = data.nama_rumah_sakit
    }

    override fun getItemCount() = datas.size

    fun setData(data: List<DataRSResponse>) {
        datas.clear()
        datas.addAll(data)
        notifyDataSetChanged()
    }

}