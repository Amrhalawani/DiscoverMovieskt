package com.amrhal.discovermovieskt.view.main.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.amrhal.discovermovieskt.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.*
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
        mPagerAdaptor = CategoriesPagerAdaptor.getInstance(activity?.supportFragmentManager!!)

        rootView.view_pager_container.adapter = mPagerAdaptor

        rootView.view_pager_container.offscreenPageLimit = 4

        rootView.view_pager_container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(rootView.tabs))

        rootView.tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(rootView.view_pager_container))
    }


}