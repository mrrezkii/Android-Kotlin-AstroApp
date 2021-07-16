package com.kedirilagi.astro.data.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.kedirilagi.astro.databinding.AdapterLokasiBinding
import com.kedirilagi.astro.network.response.DataRSResponse
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

class DataRSAdapter(
    var datas: ArrayList<DataRSResponse>,
    val listerner: OnAdapterListener
) : RecyclerView.Adapter<DataRSAdapter.ViewHolder>(), Filterable {

    private var datasFilter = ArrayList<DataRSResponse>()

    init {
        datasFilter = datas
    }

    interface OnAdapterListener {
        fun onClick(result: DataRSResponse)
    }

    class ViewHolder(val binding: AdapterLokasiBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterLokasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = datasFilter[position]
        holder.binding.tvAlamat.text = data.alamat
        holder.binding.tvNamaRumahSakit.text = data.nama_rumah_sakit
        holder.binding.container.setOnClickListener {
            listerner.onClick(data)
        }
    }

    override fun getItemCount() = datasFilter.size

    fun setData(data: List<DataRSResponse>) {
        datasFilter.clear()
        datasFilter.addAll(data)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                Timber.d("charSearch: $charSearch")
                if (charSearch.isEmpty()) {
                    datasFilter = datas
                } else {
                    val datasFiltered = ArrayList<DataRSResponse>()
                    for (data in datas) {
                        if (data.nama_rumah_sakit!!.lowercase(Locale.getDefault()).contains(
                                charSearch.lowercase(
                                    Locale.getDefault()
                                )
                            )
                        ) {
                            datasFiltered.add(data)
                        }
                    }
                    datasFilter = datasFiltered
                }
                val datasFilteredResult = FilterResults()
                datasFilteredResult.values = datasFilter
                return datasFilteredResult
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                datasFilter = results?.values as ArrayList<DataRSResponse>
                notifyDataSetChanged()
            }

        }
    }

}