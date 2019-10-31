package com.amrhal.discovermovieskt.view.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.amrhal.discovermovieskt.R
import com.amrhal.discovermovieskt.domain.core.Constants.PIC_BASE_URL_185
import com.amrhal.discovermovieskt.domain.core.Constants.PIC_BASE_URL_500
import com.amrhal.discovermovieskt.domain.core.Util
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_content_details.*
import kotlinx.android.synthetic.main.activity_details.*
import android.view.MenuItem
import com.amrhal.discovermovieskt.domain.core.Constants.MOVIE_ID_KEY
import com.amrhal.discovermovieskt.domain.entities.*
import com.amrhal.discovermovieskt.view.main.fragments.favourites.FavFragmentVM
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlin.collections.ArrayList


class DetailsActivity : AppCompatActivity() {
    var id: Int = 0
    var list: ArrayList<Actor.Cast> = arrayListOf()
    var listTrailer: ArrayList<Trailer.Result> = arrayListOf()

    private lateinit var selectedMovie: MovieDetailsRes
    var adaptor: CastAdaptor? = null
    var adaptortrailer: TrailerAdaptor? = null

    private lateinit var favVM: FavFragmentVM

    var isFavourite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        id = intent.getIntExtra(MOVIE_ID_KEY,0)

        setupActionBar()
        getMovieDetails()

        setupTrailerRV()
        getTrailers()
        setupCastRV()
        getCast()


        favVM = ViewModelProviders.of(this).get(FavFragmentVM::class.java)


        favbtn.setOnClickListener {

            isFavourite = if (!isFavourite) {
                insertToFavourites()
                true

            } else {
                deleteMovieFromFavourites()
                favbtn.setImageResource(R.drawable.ic_fav_unchecked)
                false
            }

        }
    }

    override fun onResume() {
        super.onResume()
        checkFavouriteStatus()
    }

    private fun deleteMovieFromFavourites() {
        favVM.delete(id)
    }

    private fun checkFavouriteStatus() {

        favVM.getFavMovies().observe(this, Observer { favMovies ->

            Observable.fromIterable(favMovies)
                .filter { it.id == id }
                .subscribe(object : io.reactivex.Observer<FavMovie> {
                    override fun onNext(t: FavMovie) {
                        Log.e("tag", "${t.id}")
                        favbtn.setImageResource(R.drawable.ic_fav_checked)
                        isFavourite = true
                    }

                    override fun onSubscribe(d: Disposable) {

                    }
                    override fun onError(e: Throwable) {
                        Log.e("tag", "onError: " + e.message)
                        favbtn.setImageResource(R.drawable.ic_fav_unchecked)
                        isFavourite = false
                    }
                    override fun onComplete() {
                    }
                }
                )
        })
    }

    private fun insertToFavourites() {

        val newMovie = FavMovie(
            selectedMovie.adult, selectedMovie.id,
            selectedMovie.popularity, selectedMovie.posterPath!!, selectedMovie.releaseDate,
            selectedMovie.title, selectedMovie.voteAverage
        )
        favVM.insert(newMovie)
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar_details)
        supportActionBar?.title = " "
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetTextI18n")
    private fun setupUI() {
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
            Toast.makeText(
                applicationContext,
                "cast id ${it.castId}/ id ${it.id} / creditId ${it.creditId} ",
                Toast.LENGTH_SHORT
            ).show()
        }
        recyclerview_cast.adapter = adaptor
    }

    fun getMovieDetails() {
        val model = ViewModelProviders.of(this).get(DetailActivityViewModel::class.java)
        model.getMovieDetail(id.toString()).observe(this, Observer<MovieDetailsRes> { result ->
         selectedMovie = result
            setupUI()
            progress_details.visibility = View.GONE
        })

    }

    fun getCast() {
        val model = ViewModelProviders.of(this).get(DetailActivityViewModel::class.java)
        model.getCast(id.toString()).observe(this, Observer<Actor> { result ->
            list = result.cast as ArrayList<Actor.Cast>
            if (list.size == 0) {
                layout_actors.visibility = View.GONE
            } else {
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

    fun getTrailers() {
        val modelt = ViewModelProviders.of(this).get(DetailActivityViewModel::class.java)
        modelt.getTrailer(id.toString()).observe(this, Observer<Trailer> { result ->
            listTrailer = result.results as ArrayList<Trailer.Result>
            if (listTrailer.size == 0) {
                layout_trailer_details.visibility = View.GONE
            } else {
                adaptortrailer?.updateMoviesList(listTrailer)
            }
        })
    }
}


