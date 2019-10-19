package com.amrhal.discovermovieskt.view.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.amrhal.discovermovieskt.R
import com.amrhal.discovermovieskt.domain.core.Constants.MOVIE_KEY
import com.amrhal.discovermovieskt.domain.core.Constants.PIC_BASE_URL_185
import com.amrhal.discovermovieskt.domain.core.Constants.PIC_BASE_URL_500
import com.amrhal.discovermovieskt.domain.entities.Actor
import com.amrhal.discovermovieskt.domain.entities.Movie
import com.amrhal.discovermovieskt.domain.entities.Trailer
import com.amrhal.discovermovieskt.domain.core.Util
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

        val selectedMovie = intent.getParcelableExtra<Movie.Result>(MOVIE_KEY)
        val id: String = selectedMovie.id.toString()

        setupUI(selectedMovie)
        setupTrailerRV()
        getTrailers(id)
        setupCastRV()
        getCast(id)
    }

    @SuppressLint("SetTextI18n")
    private fun setupUI(selectedMovie: Movie.Result) {
        Picasso.get().load("$PIC_BASE_URL_185${selectedMovie.posterPath}").into(poster_detail)
        avarege.text = selectedMovie.voteAverage.toString()
        titley.text = selectedMovie.title
        releasedate.text = selectedMovie.releaseDate.toString()
        over_mentID.text = selectedMovie.overview.toString()
        original_titleID.text = "${selectedMovie.originalTitle.toString()} (${selectedMovie.originalLanguage})"
        Picasso.get().load("$PIC_BASE_URL_500${selectedMovie.backdropPath}").into(backImagecollapsedID)
    }

    private fun setupCastRV() {
        recyclerview_cast.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL, false
        )

        adaptor = CastAdaptor(list as List<Actor.Cast>, this) {
            Toast.makeText(applicationContext, "${it.name}", Toast.LENGTH_SHORT).show()
        }
        recyclerview_cast.adapter = adaptor
    }

    fun getCast(id: String) {
        val model = ViewModelProviders.of(this).get(DetailActivityViewModel::class.java)
        model.getCast(id).observe(this, Observer<Actor> { result ->
            list = result.cast as ArrayList<Actor.Cast>
            if(list.size == 0){
                layout_actors.visibility = View.GONE
            }else{
                adaptor?.updateMoviesList(list)
            }

        })

    }

    private fun setupTrailerRV() {

        recyclerview_trailer.layoutManager = GridLayoutManager(this, 2)

        adaptortrailer = TrailerAdaptor(listTrailer as List<Trailer.Result>, this) {
            //  Toast.makeText(applicationContext, "${it.name} Clicked", Toast.LENGTH_SHORT).show()
            Util.watchYoutubeVideo(this, it.key)
        }
        recyclerview_trailer.adapter = adaptortrailer

    }

    fun getTrailers(id: String) {
        val modelt = ViewModelProviders.of(this).get(DetailActivityViewModel::class.java)
        modelt.getTrailer(id).observe(this, Observer<Trailer> { result ->
            listTrailer = result.results as ArrayList<Trailer.Result>
            if(listTrailer.size == 0){
                layout_trailer_details.visibility = View.GONE
            }else{
                adaptortrailer?.updateMoviesList(listTrailer)
            }
        })

    }

}