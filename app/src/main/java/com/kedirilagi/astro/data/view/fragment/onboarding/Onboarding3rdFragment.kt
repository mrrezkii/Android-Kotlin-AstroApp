package com.kedirilagi.astro.data.view.fragment.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kedirilagi.astro.databinding.FragmentOnboarding3rdBinding

class Onboarding3rdFragment : Fragment() {

    private lateinit var binding: FragmentOnboarding3rdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboarding3rdBinding.inflate(inflater, container, false)
        return binding.root
    }

}