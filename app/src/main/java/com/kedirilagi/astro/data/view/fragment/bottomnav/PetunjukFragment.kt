package com.kedirilagi.astro.data.view.fragment.bottomnav

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kedirilagi.astro.data.view.activity.MapsActivity
import com.kedirilagi.astro.databinding.FragmentPetunjukBinding
import kotlinx.coroutines.*


class PetunjukFragment : Fragment() {

    private lateinit var binding: FragmentPetunjukBinding
    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPetunjukBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        activityScope.launch {
            delay(3000)

            val intent = Intent(requireContext(), MapsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        activityScope.cancel()
    }

}