package com.amrhal.discovermovieskt.view.details

import androidx.lifecycle.LiveData

import androidx.lifecycle.ViewModel
import com.amrhal.discovermovieskt.data.model.Actor
import com.amrhal.discovermovieskt.data.model.Movie
import com.amrhal.discovermovieskt.data.model.Trailer
import com.amrhal.discovermovieskt.data.remote.ServiceRepo


class DetailActivityViewModel : ViewModel() {

    var observableActor: LiveData<Actor>? = null
    var observableTrailer: LiveData<Trailer>? = null

    fun getCast(id: String): LiveData<Actor> {
        observableActor = ServiceRepo.getMovieCast(id)
        return observableActor as LiveData<Actor>
    }

    fun getTrailer(id: String): LiveData<Trailer> {
        observableTrailer = ServiceRepo.getMovieTrailer(id)
        return observableTrailer as LiveData<Trailer>
    }


}