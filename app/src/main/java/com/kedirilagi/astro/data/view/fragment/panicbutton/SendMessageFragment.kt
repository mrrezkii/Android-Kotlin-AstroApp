package com.kedirilagi.astro.data.view.fragment.panicbutton

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.NameNotFoundException
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kedirilagi.astro.data.viewmodel.MainViewModel
import com.kedirilagi.astro.databinding.FragmentSendMessageBinding
import com.kedirilagi.astro.utils.showToast
import com.kedirilagi.astro.utils.toEditable
import timber.log.Timber


class SendMessageFragment : Fragment() {

    private lateinit var binding: FragmentSendMessageBinding
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(MainViewModel::class.java) }

    private val namaRumahSakit by lazy { requireArguments().getString("nama_rs") }
    private val alamatRumahSakit by lazy { requireArguments().getString("alamat") }
    private val nomerRumahSakit by lazy { requireArguments().getString("nomor") }

    private var lat: String? = "-7.83051223405142"
    private var long: String? = "112.02099120059324"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSendMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getPreferencesCoordinate()
    }

    private fun setupView() {
        viewModel.titleBar.postValue("Kirim Pesan Darurat")
        setupHint()
        setupObserver()
        binding.ivSend.setOnClickListener {
            val msg = binding.etMessage.text
            val uri =
                Uri.parse("https://api.whatsapp.com/send?phone=$nomerRumahSakit&text=Hallo%2C%20$namaRumahSakit.%20%0APerkenalkan%20saya%20perawat%20dari%20Alvin.%20Saat%20ini%20Alvin%20sedang%20*membutuhkan%20pertolongan%20darurat*%20dengan%20kondisi%20%3A%0A$msg%0A%20%20%20%20%0ATolong%20segera%20kirim%20bantuan%20ke%20alamat%20%3A%0Ahttps%3A%2F%2Fmaps.google.com%2Fmaps%3Fq%3D$lat%2C%20$long")
            when {
                msg.isNullOrBlank() -> showToast("Masukkan pesan darurat dulu")
                else -> {
                    val installed: Boolean = appInstallOrNot("com.whatsapp")
                    if (installed) {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data =
                            uri
                        requireActivity().startActivity(intent)
                    } else {
                        showToast("WhatsApp not Installed")
                    }
                    showToast(" lat : $lat")
                    showToast(" ling : $long")
                }
            }
        }
    }

    private fun setupObserver() {
        viewModel.preferencesCoordinate.observe(viewLifecycleOwner, Observer {
            lat = it.lat
            long = it.long
            Timber.d("Cek lat : $lat")
            Timber.d("Cek long : $long")
        })
    }

    private fun setupHint() {
        binding.hint1.setOnClickListener {
            binding.etMessage.text =
                ("${binding.etMessage.text.toString()} ${binding.hint1.text}").toEditable()
        }
        binding.hint2.setOnClickListener {
            binding.etMessage.text =
                ("${binding.etMessage.text.toString()} ${binding.hint2.text}").toEditable()
        }
        binding.hint3.setOnClickListener {
            binding.etMessage.text =
                ("${binding.etMessage.text.toString()} ${binding.hint3.text}").toEditable()
        }
        binding.hint4.setOnClickListener {
            binding.etMessage.text =
                ("${binding.etMessage.text.toString()} ${binding.hint4.text}").toEditable()
        }
        binding.hint5.setOnClickListener {
            binding.etMessage.text =
                ("${binding.etMessage.text.toString()} ${binding.hint5.text}").toEditable()
        }
        binding.hint6.setOnClickListener {
            binding.etMessage.text =
                ("${binding.etMessage.text.toString()} ${binding.hint6.text}").toEditable()
        }
        binding.hint7.setOnClickListener {
            binding.etMessage.text =
                ("${binding.etMessage.text.toString()} ${binding.hint7.text}").toEditable()
        }
        binding.hint8.setOnClickListener {
            binding.etMessage.text =
                ("${binding.etMessage.text.toString()} ${binding.hint8.text}").toEditable()
        }

    }


    private fun appInstallOrNot(url: String): Boolean {
        val packageManager: PackageManager = requireActivity().packageManager
        return try {
            packageManager.getPackageInfo(url, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: NameNotFoundException) {
            false
        }
    }

}