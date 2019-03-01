package com.amrhal.discovermovieskt.view.main.fragments

import android.annotation.SuppressLint
import android.content.Intent

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager

import com.amrhal.discovermovieskt.R
import com.amrhal.discovermovieskt.data.model.Movie
import com.amrhal.discovermovieskt.view.details.DetailsActivity
import com.amrhal.discovermovieskt.view.main.MoviesAdaptor
import com.amrhal.discovermovieskt.view.main.mainActivityViewModel
import kotlinx.android.synthetic.main.frag_home.*
import kotlinx.android.synthetic.main.frag_search.*


@SuppressLint("ValidFragment")
object SearchFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag_search, container, false)
        search_button.setOnClickListener {
            Toast.makeText(context, "btn pressed", Toast.LENGTH_SHORT).show()

        }
        return view
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
            val intent = Intent(activity?.applicationContext, DetailsActivity::class.java)
            //Todo change this implementation to parcelable later...

            intent.putExtra("movieo", it)
            startActivity(intent)
        }

        // adaptor?.updateMoviesList(list)
        recyclerviewID.adapter = adaptor
        //  Toast.makeText(activity, "list = ${list.size}", Toast.LENGTH_SHORT).show()
    }


    private fun observeFromViewModel() {

        val model = ViewModelProviders.of(this).get(mainActivityViewModel::class.java)
        model.getPopulerMovies().observe(this, Observer<Movie> { result ->
                list = result.results as ArrayList<Movie.Result>
                adaptor?.updateMoviesList(list)
                prograss_homefragment.visibility = View.GONE

            })


        }

    }


}