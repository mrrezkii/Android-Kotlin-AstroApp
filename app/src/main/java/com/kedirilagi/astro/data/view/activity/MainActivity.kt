package com.kedirilagi.astro.data.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kedirilagi.astro.R
import com.kedirilagi.astro.data.viewmodel.MainlViewModel
import com.kedirilagi.astro.data.viewmodel.factory.MainViewModelFactory
import com.kedirilagi.astro.databinding.ActivityMainBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val viewModelFactory: MainViewModelFactory by instance()
    private lateinit var viewModel: MainlViewModel
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViewModel()
        setupView()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainlViewModel::class.java)
    }

    private fun setupView() {
        setupBottomNav()
    }

    private fun setupBottomNav() {
        val navController = findNavController(R.id.hostFragment)
        binding.curveBottomBar.setupWithNavController(navController)

        binding.curveBottomBar.setOnNavigationItemSelectedListener {
            val clearNavOptions =
                NavOptions.Builder().setLaunchSingleTop(true).setPopUpTo(R.id.main_navgraph, true)
                    .build()
            when (it.itemId) {
                R.id.navigation_beranda -> {
                    navController.navigate(R.id.berandaFragment, null, clearNavOptions)
                    true
                }
                R.id.navigation_petunjuk -> {
                    navController.navigate(R.id.petunjukFragment, null, clearNavOptions)
                    true
                }
                R.id.navigation_jadwal -> {
                    navController.navigate(R.id.jadwalFragment, null, clearNavOptions)
                    true
                }
                R.id.navigation_profil -> {
                    navController.navigate(R.id.profilFragment, null, clearNavOptions)
                    true
                }
                else -> false
            }
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.curveBottomBar.isVisible = when (destination.id) {
                R.id.berandaFragment -> true
                R.id.petunjukFragment -> true
                R.id.jadwalFragment -> true
                R.id.profilFragment -> true
                else -> false
            }
        }


    }
}