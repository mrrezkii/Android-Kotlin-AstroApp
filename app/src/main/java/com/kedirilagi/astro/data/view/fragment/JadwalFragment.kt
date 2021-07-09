package com.kedirilagi.astro.data.view.fragment

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
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
        binding.tvLayoutToolbar.tvToolbar.text = "Jadwal"
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
                { _, hourOfDay, minute ->
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