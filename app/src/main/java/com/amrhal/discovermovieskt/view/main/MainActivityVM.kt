package com.amrhal.discovermovieskt.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.amrhal.discovermovieskt.domain.entities.Movie
import com.amrhal.discovermovieskt.domain.gateways.ServiceRepo


class MainActivityVM : ViewModel() {

    var MovieObservable: LiveData<Movie>? = null

    fun getPopulerMovies(): LiveData<Movie> {
        MovieObservable = ServiceRepo.getPopular()
        return MovieObservable as LiveData<Movie>
    }

    fun getTopMovies(): LiveData<Movie> {
        MovieObservable = ServiceRepo.getTopRated()
        return MovieObservable as LiveData<Movie>
    }

    fun getNowPlayingMovies(): LiveData<Movie> {
        MovieObservable = ServiceRepo.getNowPlaying()
        return MovieObservable as LiveData<Movie>
    }

    fun getUpComingMovies(): LiveData<Movie> {
        MovieObservable = ServiceRepo.getUpComing()
        return MovieObservable as LiveData<Movie>
    }

    fun getTrendingMovies(): LiveData<Movie> {
        MovieObservable = ServiceRepo.getTrending()
        return MovieObservable as LiveData<Movie>
    }

    fun getSearchedMovies(query: String): LiveData<Movie> {
        MovieObservable = ServiceRepo.getSearchedMovies(query)
        return MovieObservable as LiveData<Movie>
    }

}