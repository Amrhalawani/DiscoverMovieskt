package com.amrhal.movielix.view.main

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.amrhal.movielix.R
import com.amrhal.movielix.domain.entities.Movie
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController


    var list: ArrayList<Movie.MovieResult> = arrayListOf()


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


        private var doubleBackToExitPressedOnce = false
        override fun onBackPressed() {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed()
                return
            }
            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, getString(R.string.please_ckick_back_again_to_exit), Toast.LENGTH_SHORT).show()

            Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
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
