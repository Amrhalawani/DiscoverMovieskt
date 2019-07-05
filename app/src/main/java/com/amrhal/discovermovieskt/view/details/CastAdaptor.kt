package com.amrhal.discovermovieskt.view.details

import android.content.Context

import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.amrhal.discovermovieskt.R
import com.amrhal.discovermovieskt.data.model.Actor
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.item_actor.view.*

/**
 * Created by Amr hal on 16/01/2019.
 */

class CastAdaptor(var actorsList: List<Actor.Cast>,
    var context: Context,
    val listener: (Actor.Cast) -> Unit
) : RecyclerView.Adapter<ActorViewHolder>() {

    fun updateMoviesList(newitems: List<Actor.Cast>) {
        actorsList = newitems
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(LayoutInflater.from(context).inflate(R.layout.item_actor, parent, false))
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(actorsList[position], listener)

    }




    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        Log.e("tag","List getItemCount() = ${actorsList.size}")
        return actorsList.size
    }

}

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(actoritem: Actor.Cast, listener: (Actor.Cast) -> Unit) = with(itemView) {
        itemView.actor_pic.loadUrlPicasso(actoritem.profilePath.toString())
        itemView.character.text = actoritem.character
        itemView.actorName.text = actoritem.name


        //itemView.testText.text = movieitem.title
        setOnClickListener { listener(actoritem) }


    }

    //extension Fun
    private fun ImageView.loadUrlPicasso(url: String) {
        Picasso.get().load("https://image.tmdb.org/t/p/w185$url").into(this)

        Log.e("tag","https://image.tmdb.org/t/p/w185$url")
    }

}
