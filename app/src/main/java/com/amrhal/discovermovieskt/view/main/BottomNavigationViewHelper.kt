package com.amrhal.discovermovieskt.view.main

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx


class BottomNavigationViewHelper {
    companion object {
        fun setupBottomNavigationView(bottomNavigationViewEx: BottomNavigationViewEx) {
            bottomNavigationViewEx.enableAnimation(false)
            bottomNavigationViewEx.enableItemShiftingMode(false)
            bottomNavigationViewEx.enableShiftingMode(false)
            bottomNavigationViewEx.setTextVisibility(false)
        }
//
//        fun setupNavigation(context: Context, bottomNavigationViewEx: BottomNavigationViewEx) {
//            bottomNavigationViewEx.onNavigationItemSelectedListener =
//                    OnNavigationItemSelectedListener { item ->
//                        when (item.itemId) {
//                            R.id.home_menu_item -> {
//
//                                return@OnNavigationItemSelectedListener true
//                            }
//
//                        }
//                        false
//                    }
//        }
    }
}