package com.kedirilagi.astro.data.view.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kedirilagi.astro.R
import com.kedirilagi.astro.data.view.activity.RiwayatAktivitasActivity
import com.kedirilagi.astro.data.view.activity.RiwayatJatuhActivity
import com.kedirilagi.astro.data.view.activity.TentangAstroActivity
import com.kedirilagi.astro.data.viewmodel.ProfilViewModel
import com.kedirilagi.astro.databinding.FragmentProfilBinding

class ProfilFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(ProfilViewModel::class.java) }
    private lateinit var binding: FragmentProfilBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfilBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.tvLayoutToolbar.tvToolbar.text = getString(R.string.profil)
        binding.tvRiwayatAktivitas.setOnClickListener {
            startActivity(Intent(requireContext(), RiwayatAktivitasActivity::class.java))
        }
        binding.tvRiwayatJatuh.setOnClickListener {
            startActivity(Intent(requireContext(), RiwayatJatuhActivity::class.java))
        }
        binding.tvTentangAstro.setOnClickListener {
            startActivity(Intent(requireContext(), TentangAstroActivity::class.java))
        }
    }

    @Suppress("DEPRECATION")
    override fun onStart() {
        super.onStart()
        val window = requireActivity().window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = requireActivity().resources.getColor(R.color.colorPrimaryDark)

        val view = window.decorView
        view.systemUiVisibility = 0
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @Suppress("DEPRECATION")
    override fun onPause() {
        super.onPause()
        val window = requireActivity().window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = requireActivity().resources.getColor(R.color.colorWhite)

        val view = window.decorView
        view.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

}