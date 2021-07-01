package com.kedirilagi.astro.data.view.fragment

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kedirilagi.astro.R
import com.kedirilagi.astro.databinding.FragmentJadwalBinding
import java.util.*


class JadwalFragment : Fragment() {

    private lateinit var binding: FragmentJadwalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJadwalBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.appCompatImageView.setOnClickListener {
            setupBottomSheet()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupBottomSheet() {
        val bottomSheetDialog =
            BottomSheetDialog(requireActivity(), R.style.BottomSheetDialogTheme)
        val bottomSheetView = LayoutInflater.from(activity).inflate(
            R.layout.jadwal_bottomsheet,
            requireActivity().findViewById(R.id.jadwal_bottomsheet)
        )

        val tvTimePicker = bottomSheetView.findViewById<TextView>(R.id.tv_time_picker)

        val c = Calendar.getInstance()
        val mHour = c.get(Calendar.HOUR_OF_DAY)
        val mMinute = c.get(Calendar.MINUTE)

        tvTimePicker!!.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                requireActivity(),
                OnTimeSetListener { view, hourOfDay, minute ->
                    tvTimePicker.text = "$hourOfDay:$minute"
                },
                mHour,
                mMinute,
                false
            )
            timePickerDialog.show()
        }

        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }


}