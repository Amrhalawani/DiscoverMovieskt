package com.amrhal.discovermovieskt.view.main.fragments


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.amrhal.discovermovieskt.domain.gateways.CashingGateway.MAX_COUNT_OF_PAGER_ADAPTOR

class CategoriesPagerAdaptor(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    companion object {

        var homeFragmentsPagerAdapter: CategoriesPagerAdaptor? = null

        fun newInstance(supportFragmentManager: FragmentManager): CategoriesPagerAdaptor? {

            homeFragmentsPagerAdapter = CategoriesPagerAdaptor(supportFragmentManager)
            return homeFragmentsPagerAdapter
        }

    }


    override fun getItem(position: Int): Fragment {
        return CategoryFragment.newInstance(position)
    }

    override fun getCount(): Int {
        return MAX_COUNT_OF_PAGER_ADAPTOR
    }
}