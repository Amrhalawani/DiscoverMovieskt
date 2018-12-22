package com.amrhal.discovermovieskt.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amrhal.discovermovieskt.BuildConfig
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ServiceRepo {

    //https://proandroiddev.com/mvvm-architecture-viewmodel-and-livedata-part-1-604f50cda1

    val retrofit = RetrofitClientInstance.getInstance()?.create(MoviesApi::class.java)

    fun getTopRated(): LiveData<String> {
        val data = MutableLiveData<String>()

        retrofit?.getTopRatedMovies(BuildConfig.API_KEY)?.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                data.value = response.body()?.string()
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }
        })

        return data
    }

    //    val retrofit = RetrofitClientInstance.getInstance()?.create(MoviesApi::class.java)
    //    retrofit?.getTopRatedMovies(BuildConfig.API_KEY)?.enqueue(object : Callback<ResponseBody> {
    //
    //        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
    //            if (response.isSuccessful) {
    //                val a: String? = response.body()?.string()
    //                Log.e("tag", "a = $a ")
    //            }
    //
    //        }
    //
    //        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
    //            Log.e("tag", "Error : " + t.localizedMessage  )
    //        }
    //
    //    })
}