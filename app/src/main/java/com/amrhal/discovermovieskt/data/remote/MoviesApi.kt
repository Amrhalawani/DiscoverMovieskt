package com.amrhal.discovermovieskt.data.remote

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<ResponseBody>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Call<ResponseBody>


    @GET("movie/{id}/videos")
    fun getMovieVideos(@Path("id") id: String, @Query("api_key") apiKey: String): Call<ResponseBody>

    @GET("movie/{id}/reviews")
    fun getMovieReviews(@Path("id") id: String, @Query("api_key") apiKey: String): Call<ResponseBody>

}