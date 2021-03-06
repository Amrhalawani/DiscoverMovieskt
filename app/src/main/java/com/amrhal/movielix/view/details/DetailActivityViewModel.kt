package com.amrhal.movielix.view.details

import androidx.lifecycle.LiveData

import androidx.lifecycle.ViewModel
import com.amrhal.movielix.domain.entities.Actor
import com.amrhal.movielix.domain.entities.ActorDetailsRes
import com.amrhal.movielix.domain.entities.MovieDetailsRes
import com.amrhal.movielix.domain.entities.Trailer
import com.amrhal.movielix.domain.gateways.ServiceRepo


class DetailActivityViewModel : ViewModel() {

    var observableActor: LiveData<Actor>? = null
    var observableTrailer: LiveData<Trailer>? = null
 var observableMovieDetailsRes: LiveData<MovieDetailsRes>? = null
    var observableActorDetailsRes: LiveData<ActorDetailsRes>? = null

    fun getMovieDetail(id: String): LiveData<MovieDetailsRes> {
        observableMovieDetailsRes = ServiceRepo.getMovieDetails(id)
        return observableMovieDetailsRes as LiveData<MovieDetailsRes>
    }

    fun getActorDetails(id: String): LiveData<ActorDetailsRes> {
        observableActorDetailsRes = ServiceRepo.getActorDetails(id)
        return observableActorDetailsRes as LiveData<ActorDetailsRes>
    }

    fun getCast(id: String): LiveData<Actor> {
        observableActor = ServiceRepo.getMovieCast(id)
        return observableActor as LiveData<Actor>
    }

    fun getTrailer(id: String): LiveData<Trailer> {
        observableTrailer = ServiceRepo.getMovieTrailer(id)
        return observableTrailer as LiveData<Trailer>
    }


}