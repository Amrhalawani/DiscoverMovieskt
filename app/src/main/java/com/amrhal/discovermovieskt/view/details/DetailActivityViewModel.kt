package com.amrhal.discovermovieskt.view.details

import androidx.lifecycle.LiveData

import androidx.lifecycle.ViewModel
import com.amrhal.discovermovieskt.domain.entities.Actor
import com.amrhal.discovermovieskt.domain.entities.MovieDetailsRes
import com.amrhal.discovermovieskt.domain.entities.Trailer
import com.amrhal.discovermovieskt.domain.gateways.ServiceRepo


class DetailActivityViewModel : ViewModel() {

    var observableActor: LiveData<Actor>? = null
    var observableTrailer: LiveData<Trailer>? = null
 var observableMovieDetailsRes: LiveData<MovieDetailsRes>? = null

    fun getMovieDetail(id: String): LiveData<MovieDetailsRes> {
        observableMovieDetailsRes = ServiceRepo.getMovieDetails(id)
        return observableMovieDetailsRes as LiveData<MovieDetailsRes>
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