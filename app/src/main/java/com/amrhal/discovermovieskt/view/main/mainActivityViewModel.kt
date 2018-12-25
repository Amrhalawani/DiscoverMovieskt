package com.amrhal.discovermovieskt.view.main

import androidx.lifecycle.LiveData

import androidx.lifecycle.ViewModel
import com.amrhal.discovermovieskt.data.model.Movie
import com.amrhal.discovermovieskt.data.remote.ServiceRepo


class mainActivityViewModel : ViewModel() {

    var observable: LiveData<Movie>?=null

    fun getTopMovies():LiveData<Movie> {
        observable = ServiceRepo.getTopRated()
            return observable as LiveData<Movie>
    }
}