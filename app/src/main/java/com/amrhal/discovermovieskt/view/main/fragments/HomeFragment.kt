package com.amrhal.discovermovieskt.view.main.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.amrhal.discovermovieskt.R
import com.amrhal.discovermovieskt.domain.entities.Movie
import com.amrhal.discovermovieskt.domain.core.Constants.Companion.NOW_PLAYING_MOVIES
import com.amrhal.discovermovieskt.domain.core.Constants.Companion.POPULAR_MOVIES
import com.amrhal.discovermovieskt.domain.core.Constants.Companion.TOP_RATED_MOVIES
import com.amrhal.discovermovieskt.domain.core.Constants.Companion.UP_COMING_MOVIES
import com.amrhal.discovermovieskt.view.details.DetailsActivity
import com.amrhal.discovermovieskt.view.main.MainActivityViewModel
import com.amrhal.discovermovieskt.view.main.MoviesAdaptor
import kotlinx.android.synthetic.main.frag_home.*

class HomeFragment : Fragment() {
    var list: ArrayList<Movie.Result> = arrayListOf()
    var adaptor: MoviesAdaptor? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.frag_home, container, false)

       return rootView
    }
    companion object {
        private val ARG_SECTION_NUMBER = "section_number"

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
        recyclerviewID.layoutManager = GridLayoutManager(activity, 2)

        adaptor = MoviesAdaptor(list as List<Movie.Result>, this.activity!!) {
            val intent = Intent(activity?.applicationContext, DetailsActivity::class.java)
            intent.putExtra("movieo", it)
            startActivity(intent)
        }
        recyclerviewID.adapter = adaptor
    }


    private fun observeFromViewModel() {

        val model = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        when (arguments?.get(ARG_SECTION_NUMBER)) {
            POPULAR_MOVIES -> model.getPopulerMovies().observe(this, Observer<Movie> { result ->
                updateAdaptorList(result.results)
            })

            TOP_RATED_MOVIES -> model.getTopMovies().observe(this, Observer<Movie> { result ->
                updateAdaptorList(result.results)
            })


            NOW_PLAYING_MOVIES -> model.getNowPlayingMovies().observe(this, Observer<Movie> { result ->
                updateAdaptorList(result.results)
            })

            UP_COMING_MOVIES -> model.getUpComingMovies().observe(this, Observer<Movie> { result ->
                updateAdaptorList(result.results)

            })


        }

    }

    private fun updateAdaptorList(results: List<Movie.Result?>?) {
        adaptor?.updateMoviesList(results as List<Movie.Result>)
        prograss_homefragment.visibility = View.GONE
    }
}
