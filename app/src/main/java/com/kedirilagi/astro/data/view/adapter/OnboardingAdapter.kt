package com.kedirilagi.astro.data.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kedirilagi.astro.data.view.fragment.onboarding.Onboarding1stFragment
import com.kedirilagi.astro.data.view.fragment.onboarding.Onboarding2ndFragment
import com.kedirilagi.astro.data.view.fragment.onboarding.Onboarding3rdFragment

class OnboardingAdapter(
    fragmentManager: FragmentManager, lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragments: ArrayList<Fragment> = arrayListOf(
        Onboarding1stFragment(), Onboarding2ndFragment(), Onboarding3rdFragment()
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}