package com.amrhal.discovermovieskt.view.main.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.amrhal.discovermovieskt.domain.entities.FavMovie
import com.amrhal.discovermovieskt.domain.gateways.db.DBRepo
import com.amrhal.discovermovieskt.domain.gateways.db.FavDB
import com.amrhal.discovermovieskt.domain.gateways.db.FavMoviesDAO
import kotlinx.coroutines.launch



class FavFragmentVM (application: Application) : AndroidViewModel(application) {
    var favDao:FavMoviesDAO
    //https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/#8
    private val dbRepo: DBRepo
    var favObservable: LiveData<List<FavMovie>>? = null

    init {
        favDao = FavDB.getDatabase(application).favMoviesDAO()
        dbRepo = DBRepo(favDao)
        favObservable = dbRepo.allfavMovies

    }

    fun getFavMovies(): LiveData<List<FavMovie>> {
        favObservable = favDao.getFavMovies()
        return favObservable as LiveData<List<FavMovie>>
    }
    fun insert(word: FavMovie) = viewModelScope.launch {
        return@launch dbRepo.insert(word)
    }

    fun delete(movieId: Int) = viewModelScope.launch {
        return@launch dbRepo.delete(movieId)
    }

    }
