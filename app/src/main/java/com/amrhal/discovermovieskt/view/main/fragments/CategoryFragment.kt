package com.amrhal.discovermovieskt.view.main.fragments

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amrhal.discovermovieskt.R
import com.amrhal.discovermovieskt.domain.core.Constants.MOVIE_ID_KEY
import com.amrhal.discovermovieskt.domain.core.Constants.MOVIE_POSTER_IMG_KEY
import com.amrhal.discovermovieskt.domain.entities.Movie
import com.amrhal.discovermovieskt.domain.core.Constants.NOW_PLAYING_MOVIES
import com.amrhal.discovermovieskt.domain.core.Constants.POPULAR_MOVIES
import com.amrhal.discovermovieskt.domain.core.Constants.TOP_RATED_MOVIES
import com.amrhal.discovermovieskt.domain.core.Constants.UP_COMING_MOVIES
import com.amrhal.discovermovieskt.domain.gateways.CashingGateway.lastSelectedCategoryPos
import com.amrhal.discovermovieskt.domain.gateways.CashingGateway.rvPositionforEachPage
import com.amrhal.discovermovieskt.view.details.DetailsActivity
import com.amrhal.discovermovieskt.view.main.MainActivityVM
import com.amrhal.discovermovieskt.view.main.MoviesAdaptor
import kotlinx.android.synthetic.main.frag_category.*
import kotlinx.android.synthetic.main.item_movie.*


class CategoryFragment : Fragment() {
    var list: ArrayList<Movie.MovieResult> = arrayListOf()
    var adaptor: MoviesAdaptor? = null
    var categoryPos: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.frag_category, container, false)

        return rootView
    }

    companion object {
        private val ARG_CATEGORY_NUMBER = "category_number"

        fun newInstance(categoryNumber: Int): CategoryFragment {
            val fragment = CategoryFragment()
            val args = Bundle()
            args.putInt(ARG_CATEGORY_NUMBER, categoryNumber)
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
        rv_category.layoutManager = GridLayoutManager(activity, 2)


        adaptor = MoviesAdaptor(list as List<Movie.MovieResult>, this.activity!!) {movie, itemView->
            val intent = Intent(activity?.applicationContext, DetailsActivity::class.java)
            intent.putExtra(MOVIE_ID_KEY, movie.id)
            intent.putExtra(MOVIE_POSTER_IMG_KEY, movie.posterPath) // just to speed up the shared transition animation


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val options = ActivityOptions
                    .makeSceneTransitionAnimation(activity, itemView,  ViewCompat.getTransitionName(itemView))
                startActivity(intent, options.toBundle())
            } else {
                startActivity(intent)
            }


        }
        rv_category.adapter = adaptor

        rv_category.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    rvPositionforEachPage.set(lastSelectedCategoryPos, getCurrentItem())

                }
            }
        })
    }

    private fun getCurrentItem(): Int {
        return (rv_category.layoutManager as GridLayoutManager)
            .findFirstVisibleItemPosition()
    }

    private fun observeFromViewModel() {

        val model = ViewModelProviders.of(this).get(MainActivityVM::class.java)
        categoryPos = arguments?.get(ARG_CATEGORY_NUMBER) as Int
        when (categoryPos) {
            POPULAR_MOVIES -> model.getPopulerMovies().observe(this, Observer<Movie> { result ->
                updateAdaptorList(result.moviesList)
                updateAdaptorScrollPos(categoryPos)

            })

            TOP_RATED_MOVIES -> model.getTopMovies().observe(this, Observer<Movie> { result ->
                updateAdaptorList(result.moviesList)
                updateAdaptorScrollPos(categoryPos)
            })
            NOW_PLAYING_MOVIES -> model.getNowPlayingMovies().observe(this, Observer<Movie> { result ->
                updateAdaptorList(result.moviesList)
                updateAdaptorScrollPos(categoryPos)
            })

            UP_COMING_MOVIES -> model.getUpComingMovies().observe(this, Observer<Movie> { result ->
                updateAdaptorList(result.moviesList)
                updateAdaptorScrollPos(categoryPos)

            })


        }

    }

    private fun updateAdaptorScrollPos(categoryNumber: Int) {

        val pos = rvPositionforEachPage[categoryNumber]
        rv_category.scrollToPosition(pos)

    }

    private fun updateAdaptorList(results: List<Movie.MovieResult>?) {

        adaptor?.updateMoviesList(results as List<Movie.MovieResult>)
        prograss_category.visibility = View.GONE

    }

}
