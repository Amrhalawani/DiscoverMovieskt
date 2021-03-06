package com.amrhal.movielix.view.main.fragments

import android.content.Context
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.amrhal.movielix.R
import com.amrhal.movielix.domain.core.Constants
import com.amrhal.movielix.domain.entities.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_searched_movie.view.*

/**
 * Created by Amr hal on 1/3/2019.
 */

class SearchedMoviesAdaptor(
    var moviesList: List<Movie.MovieResult>,
    var context: Context,
    val listener: (Movie.MovieResult) -> Unit
) : RecyclerView.Adapter<MovieViewHolder>() {

    fun updateMoviesList(newitems: List<Movie.MovieResult>) {
        moviesList = newitems
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.item_searched_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(moviesList[position], listener)

    }


    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        Log.e("tag", "List getItemCount() = ${moviesList.size}")
        return moviesList.size
    }

}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(movieitem: Movie.MovieResult, listener: (Movie.MovieResult) -> Unit) = with(itemView) {

        itemView.movieposterIV_search.loadUrlPicasso(movieitem.posterPath)
        itemView.movie_name_search.text = movieitem.title
        itemView.movie_rate_search.text = "★ " + movieitem.voteAverage.toString()
        itemView.release_date_search.text = movieitem.releaseDate
        setOnClickListener { listener(movieitem) }


    }

    //extension Fun
    private fun ImageView.loadUrlPicasso(url: String?) {
        Picasso.get().load("${Constants.PIC_BASE_URL_185}$url").into(this)
    }

    //extension Fun
    private fun ImageView.loadUrlGlide(url: String) {
//Todo have a look here and fix memory leak, its consume 180mb more than picasso for 80 pic.
//
//        GlideApp.with(context).asBitmap()
//            .load(Uri.parse("https://image.tmdb.org/t/p/w185$url"))
//            .into(this)
//        Log.e("tag", "https://image.tmdb.org/t/p/w185$url")
    }

}
