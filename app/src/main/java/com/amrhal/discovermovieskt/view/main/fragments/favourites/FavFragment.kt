package com.amrhal.discovermovieskt.view.main.fragments.favourites

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
import com.amrhal.discovermovieskt.domain.core.Constants
import com.amrhal.discovermovieskt.domain.entities.FavMovie
import com.amrhal.discovermovieskt.view.details.DetailsActivity
import com.amrhal.discovermovieskt.view.main.fragments.FavMoviesAdaptor
import kotlinx.android.synthetic.main.frag_fav_movies.*
import kotlinx.android.synthetic.main.frag_fav_movies.view.*


class FavFragment : Fragment() {
    lateinit var favAdaptor: FavMoviesAdaptor

    var favlist: ArrayList<FavMovie> = arrayListOf()

    private lateinit var favViewModel: FavFragmentVM
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.frag_fav_movies, container, false)
        favViewModel = ViewModelProviders.of(this).get(FavFragmentVM::class.java)
        setupFavRV(rootView)
        return rootView
    }

    private fun setupFavRV(rootview: View) {
        rootview.rv_fav.layoutManager = GridLayoutManager(activity, 2)
        favAdaptor = FavMoviesAdaptor(
            favlist as List<FavMovie>,
            this.activity!!
        ) {
            val intent = Intent(activity?.applicationContext, DetailsActivity::class.java)
            intent.putExtra(Constants.MOVIE_ID_KEY, it.id)
            startActivity(intent)
        }

        rootview.rv_fav.adapter = favAdaptor

    }


    override fun onResume() {
        super.onResume()

        getAllFavMoviesfromViewModel()
    }
    private fun getAllFavMoviesfromViewModel() {

        favViewModel.favObservable!!.observe(this, Observer {
            progress_fav.visibility = View.GONE
             it?.let { it ->
                 favAdaptor.updateMoviesList(it)
            }
        })
}

    fun deleteMovieFromFavourites() {
        favViewModel.delete(id)
    }
}