package com.amrhal.discovermovieskt.view.main.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.amrhal.discovermovieskt.R
import com.amrhal.discovermovieskt.domain.gateways.CashingGateway.MAX_COUNT_OF_PAGER_ADAPTOR
import com.amrhal.discovermovieskt.domain.gateways.CashingGateway.lastSelectedCategoryPos
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    var mPagerAdaptor: CategoriesPagerAdaptor? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        setupPagerAdaptor(rootView)
        setHasOptionsMenu(true)

        return rootView
    }

    private fun setupPagerAdaptor(rootView: View) {
        mPagerAdaptor = CategoriesPagerAdaptor.newInstance(activity?.supportFragmentManager!!)

        rootView.view_pager_container.adapter = mPagerAdaptor


        rootView.view_pager_container.offscreenPageLimit = MAX_COUNT_OF_PAGER_ADAPTOR
        rootView.view_pager_container.currentItem = lastSelectedCategoryPos
        rootView.view_pager_container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(rootView.tabs))
        rootView.tabs.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                rootView.view_pager_container.currentItem = tab.position
                lastSelectedCategoryPos = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })
    }



}