package com.amrhal.movielix.view.main.fragments.favourites

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.amrhal.movielix.R
import com.amrhal.movielix.domain.core.Constants.PIC_BASE_URL_300
import com.amrhal.movielix.domain.core.Constants.currentYear
import com.amrhal.movielix.domain.entities.FavMovie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*


class FavMoviesAdaptor(var moviesList: List<FavMovie>,
                       var context: Context,
                       val listener: (FavMovie, View) -> Unit
) : RecyclerView.Adapter<FavMovieViewHolder>() {

    fun updateMoviesList(newitems: List<FavMovie>) {
        moviesList = newitems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMovieViewHolder {
        return FavMovieViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_movie,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavMovieViewHolder, position: Int) {
        holder.bind(moviesList[position], listener)

    }
    override fun getItemCount(): Int {
        return moviesList.size
    }

}

class FavMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(movieitem: FavMovie, listener: (FavMovie,View) -> Unit) = with(itemView) {
        itemView.image_poster_item.loadUrlPicasso(movieitem.posterPath)
        itemView.text_name_movie_item.text = movieitem.title
        itemView.text_rate_item.text = "★ " + movieitem.voteAverage.toString()

        itemView.release_date.text = if (movieitem.releaseDate?.substring(0,4) == currentYear.toString()) movieitem.releaseDate else movieitem.releaseDate?.substring(0,4)

        itemView.image_fav_sign_m_item.visibility = View.VISIBLE

        ViewCompat.setTransitionName(itemView.image_poster_item, movieitem.id.toString())
        setOnClickListener { listener(movieitem,itemView.image_poster_item) }


    }

    //extension Fun
    private fun ImageView.loadUrlPicasso(url: String) {
        Picasso.get().load("$PIC_BASE_URL_300$url").into(this)
        //Log.e("tag","https://image.tmdb.org/t/p/w185$url")
    }

}