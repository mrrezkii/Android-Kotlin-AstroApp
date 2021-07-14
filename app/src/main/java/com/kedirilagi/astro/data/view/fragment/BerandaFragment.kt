package com.kedirilagi.astro.data.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kedirilagi.astro.data.model.AktivitasModel
import com.kedirilagi.astro.data.model.ArtikelModel
import com.kedirilagi.astro.data.view.activity.StatusActivity
import com.kedirilagi.astro.data.view.adapter.AktivitasAdapter
import com.kedirilagi.astro.data.view.adapter.ArtikelAdapter
import com.kedirilagi.astro.data.viewmodel.MainViewModel
import com.kedirilagi.astro.databinding.FragmentBerandaBinding


class BerandaFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(MainViewModel::class.java) }
    private lateinit var binding: FragmentBerandaBinding
    private lateinit var adapterAktivitas: AktivitasAdapter
    private lateinit var adapterArtikel: ArtikelAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBerandaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        setAdapterAktivitas()
        setAdapterArtikel()

        binding.layoutSafe.root.setOnClickListener {
            val intent = Intent(requireContext(), StatusActivity::class.java)
            intent.putExtra("kondisi", true)
            startActivity(intent)
        }

        binding.layoutEmergency.root.setOnClickListener {
            val intent = Intent(requireContext(), StatusActivity::class.java)
            intent.putExtra("kondisi", false)
            startActivity(intent)
        }
    }

    private fun setAdapterAktivitas() {
        val listAdapter: ArrayList<AktivitasModel> = ArrayList()
        listAdapter.add(AktivitasModel(1, "Makan", "10:10"))
        listAdapter.add(AktivitasModel(2, "Pup", "14:10"))
        listAdapter.add(AktivitasModel(3, "Makan", "15:10"))
        listAdapter.add(AktivitasModel(4, "Minum", "16:10"))
        listAdapter.add(AktivitasModel(5, "Makan", "19:10"))

        adapterAktivitas = AktivitasAdapter(listAdapter)
        binding.listAktivitas.adapter = adapterAktivitas
    }

    private fun setAdapterArtikel() {
        val listAdapter: ArrayList<ArtikelModel> = ArrayList()
        listAdapter.add(
            ArtikelModel(
                1,
                "Cari Tahu Apa Itu Penyakit Stroke",
                "https://d1bpj0tv6vfxyp.cloudfront.net/articles/713965_12-1-2021_12-5-36.webp"
            )
        )
        listAdapter.add(
            ArtikelModel(
                2,
                "Mengidap Stroke Apa yang Perlu dilakukan?",
                "https://d1vbn70lmn1nqe.cloudfront.net/prod/wp-content/uploads/2021/06/15062450/mengidap-stroke-apa-yang-perlu-dilakukan-halodoc.jpg.webp"
            )
        )
        listAdapter.add(
            ArtikelModel(
                3,
                "Ketahui Penanganan Stroke Berdasarkan Jenisnya",
                "https://d1bpj0tv6vfxyp.cloudfront.net/articles/880906_12-5-2021_4-36-36.webp"
            )
        )

        adapterArtikel = ArtikelAdapter(listAdapter)
        binding.listArtikel.adapter = adapterArtikel
    }

}