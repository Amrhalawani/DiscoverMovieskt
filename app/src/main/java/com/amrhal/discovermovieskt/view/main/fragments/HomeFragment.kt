package com.amrhal.discovermovieskt.view.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.amrhal.discovermovieskt.R
import com.amrhal.discovermovieskt.data.model.Movie
import com.amrhal.discovermovieskt.view.main.MoviesAdaptor
import com.amrhal.discovermovieskt.view.main.mainActivityViewModel
import kotlinx.android.synthetic.main.frag_home.*
import kotlinx.android.synthetic.main.frag_home.view.*

class HomeFragment : Fragment() {
    var list: ArrayList<Movie.Result> = arrayListOf()
    var adaptor: MoviesAdaptor? = null
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val rootView = inflater.inflate(R.layout.frag_home, container, false)

            rootView.texthomefrag.text = getString(R.string.section_format, arguments?.getInt(ARG_SECTION_NUMBER))

            return rootView
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): HomeFragment {
                val fragment = HomeFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerViewSetup()
        observeFromViewModel()


    }
    private fun recyclerViewSetup() {

        // list = dummyList()
        recyclerviewID.layoutManager = GridLayoutManager(activity, 2)

        adaptor = MoviesAdaptor(list as List<Movie.Result>, this.activity!!) {
            Toast.makeText(activity?.applicationContext, "${it.title} Clicked", Toast.LENGTH_SHORT).show()
        }
        // adaptor?.updateMoviesList(list)
        recyclerviewID.adapter = adaptor
        Toast.makeText(activity, "list = ${list.size}", Toast.LENGTH_SHORT).show()
    }


    private fun observeFromViewModel() {

        val model = ViewModelProviders.of(this).get(mainActivityViewModel::class.java)
        when (arguments?.get(HomeFragment.ARG_SECTION_NUMBER)) {
            1 -> model.getPopulerMovies().observe(this, Observer<Movie> { result ->
                list = result.results as ArrayList<Movie.Result>
                adaptor?.updateMoviesList(list)

            })

            2 -> model.getTopMovies().observe(this, Observer<Movie> { result ->
                list = result.results as ArrayList<Movie.Result>
                adaptor?.updateMoviesList(list)

            })



            3 -> model.getNowPlayingMovies().observe(this, Observer<Movie> { result ->
                list = result.results as ArrayList<Movie.Result>
                adaptor?.updateMoviesList(list)

            })

            4 -> model.getUpComingMovies().observe(this, Observer<Movie> { result ->
                list = result.results as ArrayList<Movie.Result>
                adaptor?.updateMoviesList(list)

            })


        }

    }
    }
