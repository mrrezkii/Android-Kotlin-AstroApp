package com.kedirilagi.astro.data.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kedirilagi.astro.data.constant.artikelDummy
import com.kedirilagi.astro.data.view.activity.StatusActivity
import com.kedirilagi.astro.data.view.adapter.AktivitasAdapter
import com.kedirilagi.astro.data.view.adapter.ArtikelAdapter
import com.kedirilagi.astro.data.viewmodel.BerandaViewModel
import com.kedirilagi.astro.databinding.FragmentBerandaBinding
import com.kedirilagi.astro.extension.observe
import com.kedirilagi.astro.utils.showToast
import timber.log.Timber


class BerandaFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(BerandaViewModel::class.java) }
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
        setupObserver()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getRiwayatAktivitas()
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

    private fun setupObserver() {
        observe(viewModel.riwayatAktivitas) {
            Timber.e("data observe : $it")
            adapterAktivitas.setData(it)
        }
        observe(viewModel.message) { message ->
            showToast(message)
        }
    }

    private fun setAdapterAktivitas() {
        adapterAktivitas = AktivitasAdapter(arrayListOf())
        binding.listAktivitas.adapter = adapterAktivitas
    }

    private fun setAdapterArtikel() {
        adapterArtikel = ArtikelAdapter(artikelDummy())
        binding.listArtikel.adapter = adapterArtikel
    }

}