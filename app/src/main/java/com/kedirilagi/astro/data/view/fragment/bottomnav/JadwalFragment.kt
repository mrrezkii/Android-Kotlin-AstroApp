package com.kedirilagi.astro.data.view.fragment.bottomnav

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.TimePickerDialog
import android.graphics.Canvas
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
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kedirilagi.astro.R
import com.kedirilagi.astro.data.model.JadwalModel
import com.kedirilagi.astro.data.view.adapter.JadwalAdapter
import com.kedirilagi.astro.data.viewmodel.JadwalViewModel
import com.kedirilagi.astro.databinding.FragmentJadwalBinding
import com.kedirilagi.astro.extension.dayExtension
import com.kedirilagi.astro.extension.monthExtension
import com.kedirilagi.astro.utils.showToast
import com.kedirilagi.astro.utils.viewHide
import com.kedirilagi.astro.utils.viewShow
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.time.LocalDateTime
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
class JadwalFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(JadwalViewModel::class.java) }
    private lateinit var binding: FragmentJadwalBinding
    private lateinit var adapter: JadwalAdapter


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
        setupRecyclerView()
    }

    private fun setupView() {
        binding.tvLayoutToolbar.tvToolbar.text = getString(R.string.jadwal)
        binding.appCompatImageView.setOnClickListener {
            setupBottomSheet()
        }
    }

    private fun setupRecyclerView() {
        adapter = JadwalAdapter(arrayListOf())
        binding.listJadwal.adapter = adapter

        val simpleCallback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val entity = adapter.jadwals[position]

                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        AlertDialog.Builder(requireContext())
                            .setTitle(entity.aktivitas)
                            .setMessage("Anda Yakin Ingin Menghapus ${entity.aktivitas} ?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Hapus") { dialog, whichButton ->
                                viewModel.deleteDataJadwal(entity.id)
                                dialog.dismiss()
                                adapter.notifyDataSetChanged()
                            }
                            .setNegativeButton("Tidak") { dialog, which ->
                                dialog.dismiss()
                                adapter.notifyDataSetChanged()
                            }.show()

                    }
                }
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                RecyclerViewSwipeDecorator.Builder(
                    requireContext(),
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                    .addSwipeLeftBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.colorPrimaryDark
                        )
                    )
                    .addSwipeLeftActionIcon(R.drawable.ic_hapus)
                    .setActionIconTint(
                        ContextCompat.getColor(
                            recyclerView.context,
                            android.R.color.white
                        )
                    )
                    .create()
                    .decorate()
                super.onChildDraw(
                    c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive
                )
            }
        }

        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.listJadwal)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getDataJadwal.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapter.setData(it)
            if (it.isEmpty()) {
                binding.gif.viewShow()
                binding.gifCaption.viewShow()
                binding.listJadwal.viewHide()
            } else {
                binding.gif.viewHide()
                binding.gifCaption.viewHide()
                binding.listJadwal.viewShow()
            }
        })
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

        val mDays = c.get(Calendar.DAY_OF_MONTH)
        val mMonth = c.get(Calendar.MONTH)
        val mYear = c.get(Calendar.YEAR)

        val hari = dayExtension(mYear, mMonth, mDays)
        val bulan = monthExtension(mYear, mMonth, mDays)

        currDate = "$hari, $mDays $bulan $mYear"

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

            val hari = dayExtension(year, month, dayOfMonth)
            val bulan = monthExtension(year, month, dayOfMonth)

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
                    bottomSheetDialog.dismiss()
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