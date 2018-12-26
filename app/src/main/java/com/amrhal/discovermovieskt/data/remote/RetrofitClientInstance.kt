package com.amrhal.discovermovieskt.data.remote

import retrofit2.Retrofit


object RetrofitClientInstance {
    private val base_url = "https://api.themoviedb.org/3/"
    private var retrofit: Retrofit? = null

    fun getInstance(): Retrofit? {

        if (retrofit == null) {

            retrofit = Retrofit.Builder()
                .baseUrl(base_url)
                .build()

            return retrofit
        } else
            return retrofit
    }

}