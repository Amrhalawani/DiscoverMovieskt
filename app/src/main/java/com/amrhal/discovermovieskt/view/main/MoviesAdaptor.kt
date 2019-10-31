package com.amrhal.discovermovieskt.view.main

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.amrhal.discovermovieskt.R
import com.amrhal.discovermovieskt.domain.core.Constants.PIC_BASE_URL_300
import com.amrhal.discovermovieskt.domain.core.Constants.currentYear
import com.amrhal.discovermovieskt.domain.entities.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Created by Amr hal on 19/12/2018.
 */

class MoviesAdaptor(var moviesList: List<Movie.MovieResult>,
                    var context: Context,
                    val listener: (Movie.MovieResult) -> Unit
) : RecyclerView.Adapter<MovieViewHolder>() {

    fun updateMoviesList(newitems: List<Movie.MovieResult>) {
        moviesList = newitems
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(moviesList[position], listener)

    }
    override fun getItemCount(): Int {
        return moviesList.size
    }

}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(movieitem: Movie.MovieResult, listener: (Movie.MovieResult) -> Unit) = with(itemView) {
        itemView.image_poster_item.loadUrlPicasso(movieitem.posterPath)
        itemView.text_name_movie_item.text = movieitem.title
        itemView.text_rate_item.text = "★ " + movieitem.voteAverage.toString()

        itemView.release_date.text = if (movieitem.releaseDate?.substring(0,4) == currentYear.toString()) movieitem.releaseDate else movieitem.releaseDate?.substring(0,4)

        if(movieitem.video==false)
            itemView.image_has_video_movie_item.visibility = View.INVISIBLE
        else itemView.image_has_video_movie_item.visibility = View.VISIBLE

        setOnClickListener { listener(movieitem) }


    }

    //extension Fun
    private fun ImageView.loadUrlPicasso(url: String) {
        Picasso.get().load("$PIC_BASE_URL_300$url").into(this)
        //Log.e("tag","https://image.tmdb.org/t/p/w185$url")
    }

    //extension Fun
    private fun ImageView.loadUrlGlide(url: String) {
//Todo have a look here and fix memory leak, its consume 180mb more than picasso for 80 pic.

//        GlideApp.with(context).asBitmap()
//            .load(Uri.parse("https://image.tmdb.org/t/p/w185$url"))
//            .into(this)
//        Log.e("tag","https://image.tmdb.org/t/p/w185$url")
    }

}
