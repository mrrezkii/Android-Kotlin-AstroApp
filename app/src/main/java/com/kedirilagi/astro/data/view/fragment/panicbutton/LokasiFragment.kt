package com.kedirilagi.astro.data.view.fragment.panicbutton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kedirilagi.astro.R
import com.kedirilagi.astro.databinding.FragmentLokasiBinding

class LokasiFragment : Fragment() {

    private lateinit var binding: FragmentLokasiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLokasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        setupBottomSheet()
    }

    private fun setupBottomSheet() {
        val bottomSheetDialog =
            BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        val bottomSheetView = LayoutInflater.from(requireContext()).inflate(
            R.layout.bottomsheet_lokasi,
            requireActivity().findViewById(R.id.lokasi_bottomsheet)
        )

        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }

}