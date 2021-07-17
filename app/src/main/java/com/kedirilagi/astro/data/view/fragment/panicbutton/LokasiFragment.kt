package com.kedirilagi.astro.data.view.fragment.panicbutton

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kedirilagi.astro.R
import com.kedirilagi.astro.data.view.adapter.DataRSAdapter
import com.kedirilagi.astro.data.viewmodel.MainViewModel
import com.kedirilagi.astro.databinding.FragmentLokasiBinding
import com.kedirilagi.astro.extension.observe
import com.kedirilagi.astro.network.response.DataRSResponse
import com.kedirilagi.astro.utils.showToast
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.LocationComponentOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style

class LokasiFragment : Fragment(), OnMapReadyCallback, PermissionsListener {

    private lateinit var binding: FragmentLokasiBinding
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(MainViewModel::class.java) }
    private lateinit var adapter: DataRSAdapter
    private val bottomSheetDialog by lazy {
        BottomSheetDialog(
            requireContext(),
            R.style.BottomSheetDialogTheme
        )
    }
    private lateinit var mapboxMap: MapboxMap
    private var permissionsManager: PermissionsManager = PermissionsManager(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLokasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(savedInstanceState)
        setupRecyclerView()
        setupObserver()
    }

    private fun setupView(savedInstanceState: Bundle?) {
        viewModel.titleBar.postValue("Layanan Darurat")
        binding.btnNext.setOnClickListener {
            setupBottomSheet()
        }
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    private fun enableLocationComponent(loadedMapStyle: Style) {
        if (PermissionsManager.areLocationPermissionsGranted(requireContext())) {
            val customLocationComponentOptions = LocationComponentOptions.builder(requireContext())
                .trackingGesturesManagement(true)
                .accuracyColor(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark))
                .build()

            val locationComponentActivationOptions =
                LocationComponentActivationOptions.builder(requireContext(), loadedMapStyle)
                    .locationComponentOptions(customLocationComponentOptions)
                    .build()

            mapboxMap.locationComponent.apply {
                activateLocationComponent(locationComponentActivationOptions)
                isLocationComponentEnabled = true
                cameraMode = CameraMode.TRACKING
                renderMode = RenderMode.COMPASS
            }
        } else {
            permissionsManager = PermissionsManager(this)
            permissionsManager.requestLocationPermissions(requireActivity())
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onMapReady(mapboxMap: MapboxMap) {
        this.mapboxMap = mapboxMap
        mapboxMap.setStyle(Style.MAPBOX_STREETS) {
            enableLocationComponent(it)
        }
    }

    override fun onExplanationNeeded(p0: MutableList<String>?) {
        showToast("onExplanationNeeded")
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            enableLocationComponent(mapboxMap.style!!)
        } else {
            showToast("onPermissionResult")
            requireActivity().finish()
        }
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getDataRS()
        binding.mapView.onResume()
    }

    private fun setupObserver() {
        observe(viewModel.dataRumahSakit) {
            adapter.setData(it)
        }
        observe(viewModel.message) { message ->
            showToast(message)
        }
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.mapView.onDestroy()
    }

    private fun setupRecyclerView() {
        adapter = DataRSAdapter(arrayListOf(), object : DataRSAdapter.OnAdapterListener {
            override fun onClick(result: DataRSResponse) {
                findNavController().navigate(
                    R.id.action_lokasiFragment_to_sendMessageFragment,
                    bundleOf(
                        "nama_rs" to result.nama_rumah_sakit,
                        "alamat" to result.alamat,
                        "nomor" to result.nomer_telepon
                    )
                )
                bottomSheetDialog.dismiss()
            }
        })
    }

    private fun setupBottomSheet() {
        val bottomSheetView = LayoutInflater.from(requireContext()).inflate(
            R.layout.bottomsheet_lokasi,
            requireActivity().findViewById(R.id.lokasi_bottomsheet)
        )

        val list = bottomSheetView.findViewById<RecyclerView>(R.id.list_lokasi)
        val searchView = bottomSheetView.findViewById<EditText>(R.id.searchView)
        searchView.keyListener = null
        searchView.setOnClickListener {
            findNavController().navigate(R.id.action_lokasiFragment_to_detailLokasiFragment)
            bottomSheetDialog.dismiss()
        }

        list.adapter = adapter

        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }

}