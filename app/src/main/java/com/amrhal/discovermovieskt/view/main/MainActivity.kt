package com.amrhal.discovermovieskt.view.main

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.amrhal.discovermovieskt.R
import com.amrhal.discovermovieskt.domain.entities.Movie
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    // var bottomNavigationView: BottomNavigationViewEx? = null
    var list: ArrayList<Movie.Result> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigationComp()
    }

    private fun setupNavigationComp() {
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_activity_host_fragment) as NavHostFragment? ?: return

        navController = host.navController
        appBarConfiguration =
            AppBarConfiguration(setOf(R.id.home_frag_dest, R.id.search_frag_dest, R.id.fev_frag_dest, R.id.info_frag_dest))
        setupBottomNavMenu(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            try {
                resources.getResourceName(destination.id)

            } catch (e: Resources.NotFoundException) {
                destination.id.toString()
            }
        }
    }

    private fun setupBottomNavMenu(navController: NavController) {
        // val bottomNav = findViewById<BottomNavigationView>(R.id.main_bottom_navigation)
        bottom_nav_main?.setupWithNavController(navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)
    }
}
