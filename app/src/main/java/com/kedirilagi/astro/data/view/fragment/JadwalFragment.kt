package com.kedirilagi.astro.data.view.fragment

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kedirilagi.astro.R
import com.kedirilagi.astro.data.model.JadwalModel
import com.kedirilagi.astro.data.viewmodel.JadwalViewModel
import com.kedirilagi.astro.databinding.FragmentJadwalBinding
import com.kedirilagi.astro.utils.showToast
import java.time.LocalDateTime
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
class JadwalFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(JadwalViewModel::class.java) }
    private lateinit var binding: FragmentJadwalBinding

    var time: String = " "
    var currDate: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJadwalBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.tvLayoutToolbar.tvToolbar.text = getString(R.string.jadwal)
        binding.appCompatImageView.setOnClickListener {
            setupBottomSheet()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupBottomSheet() {
        val bottomSheetDialog =
            BottomSheetDialog(requireActivity(), R.style.BottomSheetDialogTheme)
        val bottomSheetView = LayoutInflater.from(activity).inflate(
            R.layout.bottomsheet_jadwal,
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
                    time = "$hourOfDay:$minute"
                },
                mHour,
                mMinute,
                false
            )
            timePickerDialog.show()
        }

        val tambah = bottomSheetView.findViewById<TextView>(R.id.btnTambah)
        val aktivitas = bottomSheetView.findViewById<EditText>(R.id.et_aktivitas).text
        val lokasi = bottomSheetView.findViewById<EditText>(R.id.et_lokasi).text
        val kalender = bottomSheetView.findViewById<CalendarView>(R.id.calendarView)


        kalender.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar: Calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)

            val hari = when (val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)) {
                1 -> "Minggu"
                2 -> "Senin"
                3 -> "Selasa"
                4 -> "Rabu"
                5 -> "Kamis"
                6 -> "Jum'at"
                7 -> "Sabtu"
                else -> dayOfWeek
            }

            val bulan = when (val month = calendar.get(Calendar.MONTH)) {
                0 -> "Januari"
                1 -> "Februari"
                2 -> "Maret"
                3 -> "April"
                4 -> "Mei"
                5 -> "Juni"
                6 -> "Juli"
                7 -> "Agustus"
                8 -> "September"
                9 -> "Oktober"
                10 -> "November"
                11 -> "Desember"
                else -> month
            }

            currDate = "$hari, $dayOfMonth $bulan $year"

        }

        tambah.setOnClickListener {
            val jamResult = time.filter { it.isDigit() }
            when {
                aktivitas.isNullOrBlank() -> showToast("Aktivtias harus di isi")
                lokasi.isNullOrBlank() -> showToast("Lokasi harus diisi")
                jamResult.isNullOrBlank() -> showToast("Jam harus diisi")
                currDate.isNullOrBlank() -> showToast("Tanggal harus disi")
                else -> {
                    viewModel.saveDataJadwal(
                        JadwalModel(
                            id = LocalDateTime.now().toString(),
                            aktivitas = aktivitas.toString(),
                            lokasi = lokasi.toString(),
                            jam = time,
                            tanggal = currDate
                        )
                    )
                }

            }
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