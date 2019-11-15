package com.amrhal.movielix.view.main.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.amrhal.movielix.R
import com.amrhal.movielix.domain.core.Constants

import com.amrhal.movielix.domain.entities.Movie
import com.amrhal.movielix.view.details.DetailsActivity
import com.amrhal.movielix.view.main.MainActivityVM
import kotlinx.android.synthetic.main.frag_search.view.*

@SuppressLint("ValidFragment")
class SearchFragment : Fragment() {
    var resultList: ArrayList<Movie.MovieResult> = arrayListOf()
    var adaptor: SearchedMoviesAdaptor? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag_search, container, false)
        setupSearchedMoviesRV(view)



        onClicks(view)


        return view
    }

    private fun onClicks(view: View) {
        view.btn_search.setOnClickListener {
            var query = view.et_search.text.toString()
            if (query.length >= 1){
                getSearchedMovies(query,view)
                it.visibility = View.INVISIBLE
                view.progruss_search.visibility = View.VISIBLE
            }
        }
        view.et_search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                var query = view.et_search.text.toString()
                if (query.length >= 1){ getSearchedMovies(query,view) }
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }


    private fun setupSearchedMoviesRV(view: View) {
        view.rv_search_movies.layoutManager = GridLayoutManager(activity, 1)

        adaptor = SearchedMoviesAdaptor(resultList as List<Movie.MovieResult>, this.activity!!) {
         val intent = Intent(activity?.applicationContext, DetailsActivity::class.java)
           intent.putExtra(Constants.MOVIE_ID_KEY, it.id)
            intent.putExtra(Constants.MOVIE_POSTER_IMG_KEY, it.posterPath)
            startActivity(intent)
        }
        view.rv_search_movies.adapter = adaptor
    }


    private fun getSearchedMovies(query: String, view: View) {

        val model = ViewModelProviders.of(this).get(MainActivityVM::class.java)
        model.getSearchedMovies(query).observe(this, Observer<Movie> { result ->
            resultList = result?.moviesList as ArrayList<Movie.MovieResult>
            adaptor?.updateMoviesList(resultList)

            view.progruss_search.visibility = View.GONE
            view.btn_search.visibility = View.VISIBLE

        })


    }

}