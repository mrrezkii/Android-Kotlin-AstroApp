package com.kedirilagi.astro.data.view.fragment.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kedirilagi.astro.databinding.FragmentOnboarding1stBinding

class Onboarding1stFragment : Fragment() {

    private lateinit var binding: FragmentOnboarding1stBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboarding1stBinding.inflate(inflater, container, false)
        return binding.root
    }

}