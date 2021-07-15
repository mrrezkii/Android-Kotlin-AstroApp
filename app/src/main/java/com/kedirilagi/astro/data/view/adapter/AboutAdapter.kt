package com.kedirilagi.astro.data.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kedirilagi.astro.data.view.fragment.about.About1stFragment
import com.kedirilagi.astro.data.view.fragment.about.About2ndFragment
import com.kedirilagi.astro.data.view.fragment.about.About3rdFragment
import com.kedirilagi.astro.data.view.fragment.about.About4thFragment

class AboutAdapter(
    fragmentManager: FragmentManager, lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragments: ArrayList<Fragment> = arrayListOf(
        About1stFragment(), About2ndFragment(), About3rdFragment(), About4thFragment()
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}