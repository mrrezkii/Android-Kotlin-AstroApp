package com.kedirilagi.astro.data.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kedirilagi.astro.databinding.FragmentRiwayatJatuhBinding

class RiwayatJatuhFragment : Fragment() {

    private lateinit var binding: FragmentRiwayatJatuhBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRiwayatJatuhBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.tvBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}