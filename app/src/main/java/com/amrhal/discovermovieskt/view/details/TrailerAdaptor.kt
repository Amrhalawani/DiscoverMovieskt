package com.amrhal.discovermovieskt.view.details

import android.content.Context

import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.amrhal.discovermovieskt.R
import com.amrhal.discovermovieskt.domain.entities.Trailer

import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.item_trailer.view.*

/**
 * Created by Amr hal on 16/01/2019.
 */

class TrailerAdaptor(var trailersList: List<Trailer.Result>,
    var context: Context,
    val listener: (Trailer.Result) -> Unit
) : RecyclerView.Adapter<TrailerViewHolder>() {

    fun updateMoviesList(newitems: List<Trailer.Result>) {
        trailersList = newitems
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        return TrailerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_trailer, parent, false))
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        holder.bind(trailersList[position], listener)

    }


    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        Log.e("tag", "List getItemCount() = ${trailersList.size}")
        return trailersList.size
    }

}

class TrailerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(movieTitem: Trailer.Result, listener: (Trailer.Result) -> Unit) = with(itemView) {
        val url:String =  thumbnailURL(movieTitem.key.toString())
        itemView.video_thumbnial.loadUrlPicasso(url)
        itemView.video_nameID.text = movieTitem.name
        //itemView.testText.text = movieitem.title
        setOnClickListener { listener(movieTitem) }


    }

    //extension Fun
    private fun ImageView.loadUrlPicasso(url: String) {
        Picasso.get().load(url).into(this)
        Log.e("tag", "$url")
    }

    private fun thumbnailURL(ourkey: String): String {

        val YoutubeThumbnailbase = "https://img.youtube.com/vi/"
        val endurl = "/mqdefault.jpg"
        Log.e("Tag","$YoutubeThumbnailbase + $ourkey + $endurl")
        return YoutubeThumbnailbase + ourkey + endurl
    }

}
