package com.amrhal.discovermovieskt.view.main

import android.content.Context
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.amrhal.discovermovieskt.R
import com.amrhal.discovermovieskt.data.model.Movie
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.view.*

/**
 * Created by Amr hal on 19/12/2018.
 */

class MoviesAdaptor(
    var moviesList: ArrayList<Movie.Result>,
    var context: Context,
    val listener: (Movie.Result) -> Unit
) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(moviesList[position], listener)
    }


    fun updateMoviesList(newitems: ArrayList<Movie.Result>) {
        moviesList = newitems
        notifyDataSetChanged()
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {

        return moviesList.size
    }
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(movieitem: Movie.Result, listener: (Movie.Result) -> Unit) = with(itemView) {
        itemView.movieposterIV_ID.loadUrlPicasso(movieitem.posterPath.toString())
        //itemView.testText.text = movieitem.title
        setOnClickListener { listener(movieitem) }


    }

    //extension Fun
    private fun ImageView.loadUrlPicasso(url: String) {
        Picasso.get().load("https://image.tmdb.org/t/p/w185$url").into(this)
        Log.e("tag","https://image.tmdb.org/t/p/w185$url")
    }

    //extension Fun
    private fun ImageView.loadUrlGlide(url: String) {
        Glide.with(context)
            .load(url)
            .into(this)

        //     GlideApp
//         .with(myFragment)
//         .load(url)
//         .centerCrop()
//         .placeholder(R.drawable.loading_spinner)
//         .into(myImageView)


    }

}
