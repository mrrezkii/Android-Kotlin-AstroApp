package com.kedirilagi.astro.data.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kedirilagi.astro.databinding.FragmentPetunjukBinding


class PetunjukFragment : Fragment() {

    private lateinit var binding: FragmentPetunjukBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPetunjukBinding.inflate(inflater, container, false)
        return binding.root

    }

}