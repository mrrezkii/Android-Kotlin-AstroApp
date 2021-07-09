package com.kedirilagi.astro.data.view.fragment.bottomnav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kedirilagi.astro.data.model.AktivitasModel
import com.kedirilagi.astro.data.view.adapter.AktivitasAdapter
import com.kedirilagi.astro.databinding.FragmentBerandaBinding


class BerandaFragment : Fragment() {

    private lateinit var binding: FragmentBerandaBinding
    private lateinit var adapterAktivitas: AktivitasAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBerandaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        setAdapterAktivitas()
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

}