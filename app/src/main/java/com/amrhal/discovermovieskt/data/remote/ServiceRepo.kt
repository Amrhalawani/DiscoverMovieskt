package com.amrhal.discovermovieskt.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amrhal.discovermovieskt.BuildConfig
import com.amrhal.discovermovieskt.data.model.Actor
import com.amrhal.discovermovieskt.data.model.Movie
import com.amrhal.discovermovieskt.data.model.Trailer
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ServiceRepo {

    //https://proandroiddev.com/mvvm-architecture-viewmodel-and-livedata-part-1-604f50cda1

    val retrofit = RetrofitClientInstance.getInstance()?.create(MoviesApi::class.java)

    fun getTopRated(): LiveData<Movie> {

        val data = MutableLiveData<Movie>()

        retrofit?.getTopRatedMovies(BuildConfig.API_KEY)?.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val MResponse:String? = response.body()?.string()
                val gson = Gson()
                val movie = gson.fromJson(MResponse, Movie::class.java)
                data.value = movie
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("tag","onFailure throw : ${t.localizedMessage}")
            }
        })

        return data
    }


    fun getPopular(): LiveData<Movie> {
        val data =  MutableLiveData<Movie>()
        retrofit?.getPopularMovies(BuildConfig.API_KEY)?.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val MResponse:String? = response.body()?.string()
                val gson = Gson()
                val movie = gson.fromJson(MResponse, Movie::class.java)
                data.value = movie
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("tag","onFailure throw : ${t.localizedMessage}")
            }
        })
        return data
    }

    fun getNowPlaying(): LiveData<Movie> {
        val data =  MutableLiveData<Movie>()

        retrofit?.getNowPlayingMovies(BuildConfig.API_KEY)?.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val MResponse:String? = response.body()?.string()
                val gson = Gson()
                val movie = gson.fromJson(MResponse, Movie::class.java)
                data.value = movie
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("tag","onFailure throw : ${t.localizedMessage}")
            }
        })
        return data
    }

    fun getUpComing(): LiveData<Movie> {
        val data =  MutableLiveData<Movie>()
        retrofit?.getUpComingMovies(BuildConfig.API_KEY)?.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val MResponse:String? = response.body()?.string()
                val gson = Gson()
                val movie = gson.fromJson(MResponse, Movie::class.java)
                data.value = movie
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("tag","onFailure throw : ${t.localizedMessage}")
            }
        })
        return data
    }

    fun getTrending(): LiveData<Movie> {
        val data =  MutableLiveData<Movie>()
        retrofit?.getTrendingMovies(BuildConfig.API_KEY)?.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val MResponse:String? = response.body()?.string()
                val gson = Gson()
                val movie = gson.fromJson(MResponse, Movie::class.java)
                data.value = movie
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("tag","onFailure throw : ${t.localizedMessage}")
            }
        })
        return data
    }

    fun getMovieCast(id:String): LiveData<Actor> {
        val data =  MutableLiveData<Actor>()
        retrofit?.getMovieCast(id,BuildConfig.API_KEY)?.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val MResponse:String? = response.body()?.string()
                val gson = Gson()
                val cast = gson.fromJson(MResponse, Actor::class.java)
                data.value = cast

                Log.e("tag","")
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("tag","onFailure throw : ${t.localizedMessage}")
            }
        })
        return data
    }

    fun getMovieTrailer(id:String): LiveData<Trailer> {
        val data =  MutableLiveData<Trailer>()
        retrofit?.getMovieTrailers(id,BuildConfig.API_KEY)?.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val MResponse:String? = response.body()?.string()
                val gson = Gson()
                val trailer = gson.fromJson(MResponse, Trailer::class.java)
                data.value = trailer
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("tag","onFailure throw : ${t.localizedMessage}")
            }
        })
        return data
    }

    fun getSearchedMovies(id:String): LiveData<Trailer> {
        val data =  MutableLiveData<Trailer>()
        retrofit?.searchMovie(BuildConfig.API_KEY,"sad",false)?.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val MResponse:String? = response.body()?.string()
                val gson = Gson()
                val trailer = gson.fromJson(MResponse, Trailer::class.java)
                data.value = trailer
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("tag","onFailure throw : ${t.localizedMessage}")
            }
        })
        return data
    }
}
