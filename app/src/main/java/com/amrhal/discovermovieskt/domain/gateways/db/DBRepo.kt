package com.amrhal.discovermovieskt.domain.gateways.db

import androidx.lifecycle.LiveData
import com.amrhal.discovermovieskt.domain.entities.FavMovie


class DBRepo(private val favDao: FavMoviesDAO) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allfavMovies: LiveData<List<FavMovie>> = favDao.getFavMovies()

    suspend fun insert(newMovie: FavMovie) {
        favDao.insert(newMovie)
    }
    suspend fun delete(movieId: Int) {
        favDao.delete(movieId)
    }
}