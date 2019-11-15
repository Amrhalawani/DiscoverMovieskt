package com.amrhal.movielix.domain.gateways

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<ResponseBody>

    @GET("trending/movie/week")
    fun getTrendingMovies(@Query("api_key") apiKey: String): Call<ResponseBody>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("api_key") apiKey: String): Call<ResponseBody>

    @GET("movie/upcoming")
    fun getUpComingMovies(@Query("api_key") apiKey: String): Call<ResponseBody>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Call<ResponseBody>


    @GET("movie/{id}/videos")
    fun getMovieVideos(@Path("id") id: String, @Query("api_key") apiKey: String): Call<ResponseBody>

    @GET("movie/{id}/reviews")
    fun getMovieReviews(@Path("id") id: String, @Query("api_key") apiKey: String): Call<ResponseBody>


    @GET("movie/{id}/credits")
    fun getMovieCast(@Path("id") id: String, @Query("api_key") apiKey: String): Call<ResponseBody>


    @GET("movie/{id}/videos")
    fun getMovieTrailers(@Path("id") id: String, @Query("api_key") apiKey: String): Call<ResponseBody>

    // https://api.themoviedb.org/3/search/movie?api_key=7dc3c3d78e52290fbaaca09a7fb34436&language=en-US&query=mo&page=1&include_adult=false
    @GET("search/movie")
    fun searchMovie(@Query("api_key") apiKey: String, @Query("query") query: String, @Query("page") page: String, @Query("include_adult") includeAdult: Boolean): Call<ResponseBody>

//    @GET("movie/{movie_id}")
//    fun getMovieDetails(@Query("api_key") apiKey: String, @Path("movie_id") movieId: String): Observable<MovieDetailsRes>

    @GET("movie/{movie_id}")
    fun getMovieDetails( @Path("movie_id") movieId: String, @Query("api_key") apiKey: String): Call<ResponseBody>


    @GET("person/{person_id}")
    fun getActorDetails( @Path("person_id") movieId: String, @Query("api_key") apiKey: String): Call<ResponseBody>

}

