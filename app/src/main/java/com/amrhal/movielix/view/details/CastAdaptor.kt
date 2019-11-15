package com.amrhal.movielix.view.details

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.amrhal.movielix.R
import com.amrhal.movielix.domain.core.Constants.PIC_BASE_URL_185
import com.amrhal.movielix.domain.entities.Actor
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




    override fun getItemCount(): Int {
        return actorsList.size
    }

}

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(actoritem: Actor.Cast, listener: (Actor.Cast) -> Unit) = with(itemView) {
        itemView.actor_pic.loadUrlPicasso(actoritem.profilePath.toString(), actoritem.gender!!)
        itemView.character.text = actoritem.character
        itemView.actorName.text = actoritem.name


        //itemView.testText.text = movieitem.title
        setOnClickListener { listener(actoritem) }


    }

    //extension Fun
    private fun ImageView.loadUrlPicasso(url: String,gender:Int) {
        Picasso.get()
            .load("$PIC_BASE_URL_185$url")
            .placeholder(if (gender == 2)R.drawable.ic_man_silhouette else R.drawable.ic_woman_silhouette ).into(this)

    }

}
