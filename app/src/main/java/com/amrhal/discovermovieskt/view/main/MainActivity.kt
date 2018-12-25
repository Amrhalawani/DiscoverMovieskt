package com.amrhal.discovermovieskt.view.main

import android.net.Uri
import androidx.lifecycle.Observer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.amrhal.discovermovieskt.R
import com.amrhal.discovermovieskt.data.model.Movie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var list: ArrayList<Movie.Result> = arrayListOf()
    var adaptor: MoviesAdaptor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerviewSetup()
        observeFromViewModel()

    }

    private fun recyclerviewSetup() {

        //list = dummyList()

        recyclerviewID.layoutManager = GridLayoutManager(this, 2)
        adaptor = MoviesAdaptor(list, this) {
            Toast.makeText(this, "${it.title} Clicked", Toast.LENGTH_SHORT).show()
        }
        recyclerviewID.adapter = adaptor
        adaptor?.updateMoviesList(list)

    }

    private fun dummyList(): ArrayList<Movie.Result> {
        val listid = arrayListOf<Int>()
        listid.add(6)

        val list = arrayListOf<Movie.Result>()
        for (i in 1..3) {
            list.add(
                Movie.Result(
                    false,
                    "",
                    listid,
                    i,
                    "",
                    "",
                    "",
                    1.1,
                    "https://appledentures.ca/wp-content/uploads/2015/11/person1.jpg",
                    "",
                    "title $i",
                    false,
                    0.0,
                    2
                )
            )
        }
        return list
    }

    private fun observeFromViewModel() {

        val model = ViewModelProviders.of(this).get(mainActivityViewModel::class.java)

        model.getTopMovies().observe(this, Observer<Movie> { result ->
            Toast.makeText(this, "Movie Delivered", Toast.LENGTH_SHORT).show()
            list = result.results as ArrayList<Movie.Result>
            //list = dummyList()
            adaptor?.updateMoviesList(list)
        })
    }

}
