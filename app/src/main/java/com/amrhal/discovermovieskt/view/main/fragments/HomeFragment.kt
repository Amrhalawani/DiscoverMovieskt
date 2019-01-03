package com.amrhal.discovermovieskt.view.main.fragments

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.Observer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.amrhal.discovermovieskt.R
import com.amrhal.discovermovieskt.data.model.Movie
import com.amrhal.discovermovieskt.util.GlideApp
import com.amrhal.discovermovieskt.view.main.MoviesAdaptor
import com.amrhal.discovermovieskt.view.main.mainActivityViewModel

import com.bumptech.glide.request.target.BitmapImageViewTarget
import kotlinx.android.synthetic.main.frag_home.*

class HomeFragment : Fragment() {


    var list: ArrayList<Movie.Result> = arrayListOf()
    var adaptor: MoviesAdaptor? = null

    companion object {
        private var homeFragment:HomeFragment? = null
        fun getInstance():HomeFragment{

            if (homeFragment == null) { //singlton pettern
                homeFragment= HomeFragment()

                return homeFragment as HomeFragment
            }
            else
                return homeFragment as HomeFragment
        }

    }


    private val imageUrl = "https://static.pexels.com/photos/596940/pexels-photo-596940.jpeg"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_home,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerviewSetup()
        observeFromViewModel()
    }

    private fun recyclerviewSetup() {

       // list = dummyList()
        recyclerviewID.layoutManager = GridLayoutManager(activity, 2)

        adaptor = MoviesAdaptor(list as List<Movie.Result>, this.activity!!) {
            Toast.makeText(activity , "${it.title} Clicked", Toast.LENGTH_SHORT).show()
        }
       // adaptor?.updateMoviesList(list)
        recyclerviewID.adapter = adaptor
        Toast.makeText(activity, "list = ${list.size}", Toast.LENGTH_SHORT).show()
    }
    private fun observeFromViewModel() {

        val model = ViewModelProviders.of(this).get(mainActivityViewModel::class.java)

        model.getPopulerMovies().observe(this, Observer<Movie> { result ->
            Toast.makeText(activity, "Movie Delivered", Toast.LENGTH_SHORT).show()
            list = result.results as ArrayList<Movie.Result>
            adaptor?.updateMoviesList(list)

        })
    }

}