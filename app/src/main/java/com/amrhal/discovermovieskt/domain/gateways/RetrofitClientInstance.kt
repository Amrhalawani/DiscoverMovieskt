package com.amrhal.discovermovieskt.domain.gateways

import retrofit2.Retrofit


object RetrofitClientInstance {
    private val base_url = "https://api.themoviedb.org/3/"
    private var retrofit: Retrofit? = null

    fun getInstance(): Retrofit? {

        return if (retrofit == null) {

            retrofit = Retrofit.Builder()
                .baseUrl(base_url)
                .build()

            retrofit
        } else
            retrofit
    }

}