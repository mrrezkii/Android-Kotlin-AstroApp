package com.kedirilagi.astro.data.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kedirilagi.astro.databinding.FragmentJadwalBinding


class JadwalFragment : Fragment() {

    private lateinit var binding: FragmentJadwalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJadwalBinding.inflate(inflater, container, false)
        return binding.root

    }

}