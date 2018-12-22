package com.amrhal.discovermovieskt.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.amrhal.discovermovieskt.BuildConfig
import com.amrhal.discovermovieskt.data.remote.MoviesApi
import com.amrhal.discovermovieskt.data.remote.RetrofitClientInstance
import com.amrhal.discovermovieskt.R
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitClientInstance.getInstance()?.create(MoviesApi::class.java)
        retrofit?.getTopRatedMovies(BuildConfig.API_KEY)?.enqueue(object : Callback<ResponseBody> {

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val a: String? = response.body()?.string()
             Log.e("tag", "a = $a ")
                }

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("tag", "Error : " + t.localizedMessage  )
            }

        })

    }
}
