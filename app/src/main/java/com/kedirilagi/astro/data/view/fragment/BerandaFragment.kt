package com.kedirilagi.astro.data.view.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kedirilagi.astro.data.constant.artikelDummy
import com.kedirilagi.astro.data.view.activity.StatusActivity
import com.kedirilagi.astro.data.view.adapter.AktivitasAdapter
import com.kedirilagi.astro.data.view.adapter.ArtikelAdapter
import com.kedirilagi.astro.data.viewmodel.BerandaViewModel
import com.kedirilagi.astro.databinding.FragmentBerandaBinding
import com.kedirilagi.astro.extension.dayExtension
import com.kedirilagi.astro.extension.monthExtension
import com.kedirilagi.astro.extension.observe
import com.kedirilagi.astro.utils.showToast
import com.kedirilagi.astro.utils.viewHide
import com.kedirilagi.astro.utils.viewShow
import timber.log.Timber
import java.util.*


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

    @RequiresApi(Build.VERSION_CODES.O)
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

        val c = Calendar.getInstance()

        val mDays = c.get(Calendar.DAY_OF_MONTH)
        val mMonth = c.get(Calendar.MONTH)
        val mYear = c.get(Calendar.YEAR)

        val hari = dayExtension(mYear, mMonth, mDays)
        val bulan = monthExtension(mYear, mMonth, mDays)

        val currDate = "$mDays $bulan $mYear"

        binding.tvHari.text = hari.toString()
        binding.tvTanggal.text = currDate


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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupObserver() {
        observe(viewModel.riwayatAktivitas) {
            Timber.e("data observe : $it")
            Collections.reverse(it)
            adapterAktivitas.setData(it)
        }
        observe(viewModel.message) { message ->
            showToast(message)
        }
        viewModel.getFirstDataJadwal.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it == null) {
                binding.ivSad.viewShow()
                binding.tvSad.viewShow()

                binding.view.viewHide()
                binding.tvTanggalJadwal.viewHide()
                binding.tvAktivitas.viewHide()
                binding.tvLokasi.viewHide()
                binding.tvJam.viewHide()

            } else {
                binding.ivSad.viewHide()
                binding.tvSad.viewHide()

                binding.view.viewShow()
                binding.tvTanggalJadwal.viewShow()
                binding.tvAktivitas.viewShow()
                binding.tvLokasi.viewShow()
                binding.tvJam.viewShow()

                binding.tvTanggalJadwal.text = it.tanggal
                binding.tvAktivitas.text = it.aktivitas
                binding.tvLokasi.text = it.lokasi
                binding.tvJam.text = it.jam
            }
        })
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