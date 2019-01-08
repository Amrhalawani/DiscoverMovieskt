package com.amrhal.discovermovieskt.view.main.fragments

import android.os.Bundle
import androidx.lifecycle.Observer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.amrhal.discovermovieskt.R
import com.amrhal.discovermovieskt.data.model.Movie

import com.amrhal.discovermovieskt.view.main.MoviesAdaptor
import com.amrhal.discovermovieskt.view.main.mainActivityViewModel

import kotlinx.android.synthetic.main.frag_home.*

class HomeFragment : Fragment() {
    private val imageUrl = "https://static.pexels.com/photos/596940/pexels-photo-596940.jpeg"

    var list: ArrayList<Movie.Result> = arrayListOf()
    var adaptor: MoviesAdaptor? = null

    companion object {
        val ARG_SECTION_NUMBER = "pos"
        private var homeFragment: HomeFragment? = null
        fun newInstance(pos: Int): HomeFragment {
            homeFragment = HomeFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, pos)
            homeFragment?.arguments = args

            return homeFragment as HomeFragment
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_home, container, false)
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
            Toast.makeText(activity, "${it.title} Clicked", Toast.LENGTH_SHORT).show()
        }
        // adaptor?.updateMoviesList(list)
        recyclerviewID.adapter = adaptor
        Toast.makeText(activity, "list = ${list.size}", Toast.LENGTH_SHORT).show()
    }

    private fun observeFromViewModel() {

        val model = ViewModelProviders.of(this).get(mainActivityViewModel::class.java)
        when (arguments?.get(ARG_SECTION_NUMBER)) {
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