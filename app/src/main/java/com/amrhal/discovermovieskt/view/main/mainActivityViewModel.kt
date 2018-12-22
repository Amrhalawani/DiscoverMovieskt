package com.amrhal.discovermovieskt.view.main

import androidx.lifecycle.LiveData

import androidx.lifecycle.ViewModel
import com.amrhal.discovermovieskt.data.remote.ServiceRepo


class mainActivityViewModel : ViewModel() {

    var observable: LiveData<String>?=null

    fun getTopMovies():LiveData<String> {
        observable = ServiceRepo.getTopRated()
            return observable as LiveData<String>
    }
}