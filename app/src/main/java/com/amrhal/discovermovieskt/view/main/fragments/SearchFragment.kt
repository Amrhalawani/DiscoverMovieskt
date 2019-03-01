package com.amrhal.discovermovieskt.view.main.fragments

import android.annotation.SuppressLint
import android.content.Intent
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
import com.amrhal.discovermovieskt.view.details.DetailsActivity
import com.amrhal.discovermovieskt.view.main.MainActivityViewModel
import kotlinx.android.synthetic.main.frag_search.*
import kotlinx.android.synthetic.main.frag_search.view.*

@SuppressLint("ValidFragment")
object SearchFragment : Fragment() {
    var resultList: ArrayList<Movie.Result> = arrayListOf()
    var adaptor: SearchedMoviesAdaptor? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag_search, container, false)
        recyclerViewSetup(view)


        view.search_button.setOnClickListener {
            var query = view.Search_editText.text.toString()
            if (query.length >= 1){
                observeFromViewModel(query,view)
                it.visibility = View.INVISIBLE
                view.search_progruss.visibility = View.VISIBLE
            }
            Toast.makeText(activity?.applicationContext, "query = $query", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    override fun onResume() {
        super.onResume()

    }

    private fun recyclerViewSetup(view: View) {
        view.search_movies_recyclerview.layoutManager = GridLayoutManager(activity, 1)

        adaptor = SearchedMoviesAdaptor(resultList as List<Movie.Result>, this.activity!!) {
            Toast.makeText(activity?.applicationContext, "${it.title} Clicked", Toast.LENGTH_SHORT).show()
         val intent = Intent(activity?.applicationContext, DetailsActivity::class.java)
           //Todo change this implementation to parcelable later...

           intent.putExtra("movieo", it)
            startActivity(intent)
        }

        // adaptor?.updateMoviesList(list)
        view.search_movies_recyclerview.adapter = adaptor
        //  Toast.makeText(activity, "list = ${list.size}", Toast.LENGTH_SHORT).show()
    }


    private fun observeFromViewModel(query: String, view: View) {

        val model = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        model.getSearchedMovies(query).observe(this, Observer<Movie> { result ->
            resultList = result?.results as ArrayList<Movie.Result>
            adaptor?.updateMoviesList(resultList)

            view.search_progruss.visibility = View.GONE
            view.search_button.visibility = View.VISIBLE

        })


    }

}