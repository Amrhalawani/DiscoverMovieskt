package com.amrhal.discovermovieskt.view.main

import androidx.lifecycle.Observer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.amrhal.discovermovieskt.R
import com.amrhal.discovermovieskt.data.model.Movie
import kotlinx.android.synthetic.main.activity_main.*
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.gigamole.navigationtabstrip.NavigationTabStrip
import androidx.viewpager.widget.ViewPager
import com.amrhal.discovermovieskt.view.main.fragments.FavFragment
import com.amrhal.discovermovieskt.view.main.fragments.HomeFragment
import com.amrhal.discovermovieskt.view.main.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    val mHomeFragment = HomeFragment.getInstance()
    val mSearchFragment = SearchFragment.getInstance()
    val mFavFragment = FavFragment.getInstance()


    var bottomNavigationView: BottomNavigationViewEx? = null


    private val mViewPager: ViewPager? = null
    var mTopNavigationTabStrip: NavigationTabStrip? = null


    var list: ArrayList<Movie.Result> = arrayListOf()
    var adaptor: MoviesAdaptor? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBottomNav()

        setupTabLayout()


        // recyclerviewSetup()

       // observeFromViewModel()
        button.setOnClickListener {

            bottomNavigationView?.visibility = View.GONE
        }


    }

    private fun setupTabLayout() {
        mTopNavigationTabStrip = findViewById(R.id.nts_top)
        mTopNavigationTabStrip?.setTabIndex(0, true)
    }


//    fun initUI() {
//        mViewPager = (ViewPager) findViewById(R.id.vp);
//        mTopNavigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts_top);
//
//    }
//
//    fun setUI() {
//        mViewPager.setAdapter( PagerAdapter() {
//            @Override
//            public int getCount() {
//                return 3;
//            }
//
//            @Override
//            public boolean isViewFromObject(final View view, final Object object) {
//                return view.equals(object);
//            }
//
//            @Override
//            public void destroyItem(final View container, final int position, final Object object) {
//                ((ViewPager) container).removeView((View) object);
//            }
//
//            @Override
//            public Object instantiateItem(final ViewGroup container, final int position) {
//                final View view = new View(getBaseContext());
//                container.addView(view);
//                return view;
//            }}
//        })
//
//        mTopNavigationTabStrip.setTabIndex(1, true)


    private fun setupBottomNav() {
        bottomNavigationView = findViewById<BottomNavigationViewEx>(R.id.main_bottom_navigation)
        bottomNavigationView?.enableAnimation(true)
        bottomNavigationView?.enableItemShiftingMode(false)
        bottomNavigationView?.enableShiftingMode(true)
        bottomNavigationView?.setTextVisibility(true)


        bottomNavigationView?.onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {


            when (it.itemId) {
                R.id.home_menu_item -> {
                    setupFragment(mHomeFragment)
                    mTopNavigationTabStrip?.visibility = View.VISIBLE
                    return@OnNavigationItemSelectedListener true
                }
                R.id.search_menu_item -> {
                    setupFragment(mSearchFragment)
                    mTopNavigationTabStrip?.visibility = View.GONE
                    return@OnNavigationItemSelectedListener true
                }
                R.id.fev_movies_menu_item -> {
                    setupFragment(mFavFragment)
                    mTopNavigationTabStrip?.visibility = View.GONE
                    return@OnNavigationItemSelectedListener true
                }


            }
            return@OnNavigationItemSelectedListener false

        }


    }

    private fun recyclerviewSetup() {

//       // list = dummyList()
//        recyclerviewID.layoutManager = GridLayoutManager(applicationContext, 2)
//
//        adaptor = MoviesAdaptor(list, this) {
//            Toast.makeText(this, "${it.title} Clicked", Toast.LENGTH_SHORT).show()
//        }
//       // adaptor?.updateMoviesList(list)
//        recyclerviewID.adapter = adaptor
//        Toast.makeText(this, "list = ${list.size}", Toast.LENGTH_SHORT).show()
    }

    private fun dummyList(): ArrayList<Movie.Result> {
        val listid = List<Int>(2) { 5 }


        val listf = arrayListOf<Movie.Result>()
        for (i in 1..5) {
            listf.add(
                Movie.Result(
                    false,
                    "a",
                    listid,
                    i,
                    "a",
                    "a",
                    "a",
                    1.1,
                    "https://appledentures.ca/wp-content/uploads/2015/11/person1.jpg",
                    "a",
                    "title $i",
                    false,
                    0.05,
                    2
                )
            )
        }
        return listf
    }

//    private fun observeFromViewModel() {
//
//        val model = ViewModelProviders.of(this).get(mainActivityViewModel::class.java)
//
//        model.getTopMovies().observe(this, Observer<Movie> { result ->
//            Toast.makeText(this, "Movie Delivered", Toast.LENGTH_SHORT).show()
//            list = result.results as ArrayList<Movie.Result>
//            adaptor?.updateMoviesList(list)
//
//        })
//    }


    private fun setupFragment(fragment: Fragment) { //using polymorphism

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

}
