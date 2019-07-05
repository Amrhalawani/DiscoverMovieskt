package com.amrhal.discovermovieskt.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.amrhal.discovermovieskt.R
import com.amrhal.discovermovieskt.data.model.Movie
import kotlinx.android.synthetic.main.activity_main.*
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx

import com.amrhal.discovermovieskt.view.main.fragments.FavFragment
import com.amrhal.discovermovieskt.view.main.fragments.HomeFragment
import com.amrhal.discovermovieskt.view.main.fragments.InfoFragment

import com.amrhal.discovermovieskt.view.main.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {

    var bottomNavigationView: BottomNavigationViewEx? = null
    private var mHomeFragmentsPagerAdapter: FragmentsPagerAdapter? = null
    var list: ArrayList<Movie.Result> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBottomNav()
        actionberSetup()
        mHomeFragmentsPagerAdapter = FragmentsPagerAdapter.getInstance(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        view_pager_container.adapter = mHomeFragmentsPagerAdapter

        view_pager_container.offscreenPageLimit = 4


        view_pager_container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))

        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(view_pager_container))


    }

    private fun actionberSetup() {


    }


    private fun setupBottomNav() {
        bottomNavigationView = findViewById<BottomNavigationViewEx>(R.id.main_bottom_navigation)
        bottomNavigationView?.enableAnimation(true)
        bottomNavigationView?.enableItemShiftingMode(false)
        bottomNavigationView?.enableShiftingMode(true)
        bottomNavigationView?.setTextVisibility(true)


        bottomNavigationView?.onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.home_menu_item -> {
                    tabs.visibility = View.VISIBLE
                    view_pager_container.visibility = View.VISIBLE
                    removeFragment()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.search_menu_item -> {
                    replaceFragment(FavFragment, SearchFragment)
                    tabs.visibility = View.GONE
                    view_pager_container.visibility = View.GONE
                    return@OnNavigationItemSelectedListener true
                }
                R.id.fev_movies_menu_item -> {
                    replaceFragment(SearchFragment, FavFragment)
                    tabs.visibility = View.GONE
                    view_pager_container.visibility = View.GONE
                    return@OnNavigationItemSelectedListener true
                }
                R.id.info_item -> {
                    replaceFragment(SearchFragment, InfoFragment)
                    tabs.visibility = View.GONE
                    view_pager_container.visibility = View.GONE
                    return@OnNavigationItemSelectedListener true
                }


            }
            return@OnNavigationItemSelectedListener false

        }


    }

    private fun removeFragment() {
        supportFragmentManager.beginTransaction().remove(SearchFragment)
        supportFragmentManager.beginTransaction().remove(FavFragment)
        //  for (fragment in supportFragmentManager.fragments) { its works well but it will remove the viewpager fragments too
        //     supportFragmentManager.beginTransaction().remove(fragment).commit()
        //}
    }

    private fun replaceFragment(fromFragment: Fragment, toFragment: Fragment) { //using polymorphism

        supportFragmentManager.beginTransaction().remove(fromFragment).commit()
        if (!toFragment.isAdded) {
            supportFragmentManager.beginTransaction().add(R.id.frame_layout, toFragment).commit()
        }

    }


    class FragmentsPagerAdapter (fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        companion object {

            var homeFragmentsPagerAdapter: FragmentsPagerAdapter? = null
            fun getInstance(supportFragmentManager: FragmentManager): FragmentsPagerAdapter? {  //singleton pattern

                if (homeFragmentsPagerAdapter == null) {
                    homeFragmentsPagerAdapter = FragmentsPagerAdapter(supportFragmentManager)

                    return homeFragmentsPagerAdapter
                } else
                    return homeFragmentsPagerAdapter
            }

        }


        override fun getItem(position: Int): Fragment {
            return HomeFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            return 4
        }
    }

}
