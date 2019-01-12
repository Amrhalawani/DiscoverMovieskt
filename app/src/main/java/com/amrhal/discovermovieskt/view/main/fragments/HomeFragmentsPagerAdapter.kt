//package com.amrhal.discovermovieskt.view.main.fragments
//
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//
//import androidx.fragment.app.FragmentStatePagerAdapter
//
//class HomeFragmentsPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
//
//    companion object {
//
//        var homeFragmentsPagerAdapter:HomeFragmentsPagerAdapter? = null
//        fun getInstance(supportFragmentManager:FragmentManager):HomeFragmentsPagerAdapter?{  //singleton pattern
//
//            if (homeFragmentsPagerAdapter == null) {
//                homeFragmentsPagerAdapter= HomeFragmentsPagerAdapter( supportFragmentManager )
//
//                return homeFragmentsPagerAdapter
//            }
//            else
//                return homeFragmentsPagerAdapter
//        }
//
//    }
//
//
//    override fun getItem(position: Int): Fragment {
//        // getItem is called to instantiate the fragment for the given page.
//        // Return a PlaceholderFragment (defined as a static inner class below).
//        return HomeFragment.newInstance(position + 1)
//    }
//
//    override fun getCount(): Int {
//        // Show 3 total pages.
//        return 4
//    }
//}