package com.kedirilagi.astro.data.view.fragment.panicbutton

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kedirilagi.astro.data.viewmodel.MainViewModel
import com.kedirilagi.astro.databinding.FragmentSendMessageBinding

class SendMessageFragment : Fragment() {

    private lateinit var binding: FragmentSendMessageBinding
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(MainViewModel::class.java) }

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

    private fun setupView() {
        viewModel.titleBar.postValue("Layanan Darurat")
        setupHint()
    }

    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

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

}