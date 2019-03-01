package com.amrhal.discovermovieskt.view.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager

import com.amrhal.discovermovieskt.R
import com.amrhal.discovermovieskt.data.model.Actor
import com.amrhal.discovermovieskt.data.model.Movie
import com.amrhal.discovermovieskt.data.model.Trailer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_content_details.*
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    var list: ArrayList<Actor.Cast> = arrayListOf()
    var listTrailer: ArrayList<Trailer.Result> = arrayListOf()

    var adaptor: CastAdaptor? = null
    var adaptortrailer: TrailerAdaptor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val selectedMovie = intent.getParcelableExtra<Movie.Result>("movieo")
        val id: String = selectedMovie.id.toString()

        setupUI(selectedMovie)

        TrailerRecyclerViewSetup()
        getTrailers(id)

        castRecyclerViewSetup()
        getCast(id)
    }

    @SuppressLint("SetTextI18n")
    private fun setupUI(selectedMovie: Movie.Result) {
        Picasso.get().load("https://image.tmdb.org/t/p/w185${selectedMovie.posterPath}").into(poster_detail)
        avarege.text = selectedMovie.voteAverage.toString()
        titley.text = selectedMovie.title
        releasedate.text = selectedMovie.releaseDate.toString()
        over_mentID.text = selectedMovie.overview.toString()
        original_titleID.text = "${selectedMovie.originalTitle.toString()} (${selectedMovie.originalLanguage})"
        Picasso.get().load("https://image.tmdb.org/t/p/w500${selectedMovie.backdropPath}").into( backImagecollapsedID )
    }

    private fun castRecyclerViewSetup() {

        recyclerview_cast.layoutManager = GridLayoutManager(this, 4)

        adaptor = CastAdaptor(list as List<Actor.Cast>, this) {
            Toast.makeText(applicationContext, "${it.name} Clicked", Toast.LENGTH_SHORT).show()
        }
        recyclerview_cast.adapter = adaptor
        Toast.makeText(applicationContext, "list = ${list.size}", Toast.LENGTH_SHORT).show()
    }

    fun getCast(id: String) {
        val model = ViewModelProviders.of(this).get(DetailActivityViewModel::class.java)
        model.getCast(id).observe(this, Observer<Actor> { result ->
            list = result.cast as ArrayList<Actor.Cast>
            adaptor?.updateMoviesList(list)

        })

    }

    private fun TrailerRecyclerViewSetup() {

        recyclerview_trailer.layoutManager = GridLayoutManager(this, 2)

        adaptortrailer = TrailerAdaptor(listTrailer as List<Trailer.Result>, this) {
            Toast.makeText(applicationContext, "${it.name} Clicked", Toast.LENGTH_SHORT).show()


        }

        recyclerview_trailer.adapter = adaptortrailer
        Toast.makeText(applicationContext, "list = ${list.size}", Toast.LENGTH_SHORT).show()

    }

    fun getTrailers(id: String) {
        val modelt = ViewModelProviders.of(this).get(DetailActivityViewModel::class.java)
        modelt.getTrailer(id).observe(this, Observer<Trailer> { result ->
            listTrailer = result.results as ArrayList<Trailer.Result>
            adaptortrailer?.updateMoviesList(listTrailer)

            Toast.makeText(this, "${listTrailer.size}", Toast.LENGTH_SHORT).show()
        })

    }

}