package com.amrhal.discovermovieskt.view.main

import androidx.lifecycle.LiveData

import androidx.lifecycle.ViewModel
import com.amrhal.discovermovieskt.data.model.Movie
import com.amrhal.discovermovieskt.data.remote.ServiceRepo


class mainActivityViewModel : ViewModel() {

    var observable: LiveData<Movie>?=null

    fun getPopulerMovies():LiveData<Movie> {
        observable = ServiceRepo.getPopular()
        return observable as LiveData<Movie>
    }
    fun getTopMovies():LiveData<Movie> {
        observable = ServiceRepo.getTopRated()
            return observable as LiveData<Movie>
    }
    fun getNowPlayingMovies():LiveData<Movie> {
        observable = ServiceRepo.getNowPlaying()
            return observable as LiveData<Movie>
    }

    fun getUpComingMovies():LiveData<Movie> {
        observable = ServiceRepo.getUpComing()
            return observable as LiveData<Movie>
    }

    fun getTrendingMovies():LiveData<Movie> {
        observable = ServiceRepo.getTrending()
            return observable as LiveData<Movie>
    }

}