package com.amrhal.discovermovieskt.view.main

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

       // recyclerviewSetup()

        recyclerviewID.layoutManager = GridLayoutManager(applicationContext, 2)

        adaptor = MoviesAdaptor(list, this) {
            Toast.makeText(this, "${it.title} Clicked", Toast.LENGTH_SHORT).show()
        }
        recyclerviewID.adapter = adaptor
        observeFromViewModel()
    }

    private fun recyclerviewSetup() {

        list = dummyList()

        recyclerviewID.layoutManager = GridLayoutManager(applicationContext, 2)

        adaptor = MoviesAdaptor(list, this) {
            Toast.makeText(this, "${it.title} Clicked", Toast.LENGTH_SHORT).show()
        }
        adaptor?.updateMoviesList(list)
        recyclerviewID.adapter = adaptor

        Toast.makeText(this, "list = ${list.size}", Toast.LENGTH_SHORT).show()

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

    private fun observeFromViewModel() {

        val model = ViewModelProviders.of(this).get(mainActivityViewModel::class.java)

        model.getTopMovies().observe(this, Observer<Movie> { result ->
            Toast.makeText(this, "Movie Delivered", Toast.LENGTH_SHORT).show()
            list = result.results as ArrayList<Movie.Result>


            adaptor?.updateMoviesList(list)

        })
    }


}
