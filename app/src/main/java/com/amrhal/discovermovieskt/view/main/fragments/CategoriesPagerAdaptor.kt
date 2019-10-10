package com.amrhal.discovermovieskt.view.main.fragments


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class CategoriesPagerAdaptor (fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    companion object {

        var homeFragmentsPagerAdapter: CategoriesPagerAdaptor? = null
        fun getInstance(supportFragmentManager: FragmentManager): CategoriesPagerAdaptor? {  //singleton pattern

            if (homeFragmentsPagerAdapter == null) {
                homeFragmentsPagerAdapter = CategoriesPagerAdaptor(supportFragmentManager)

                return homeFragmentsPagerAdapter
            } else
                return homeFragmentsPagerAdapter
        }

    }


    override fun getItem(position: Int): Fragment {
        return CategoryFragment.newInstance(position + 1)
    }

    override fun getCount(): Int {
        return 4
    }
}