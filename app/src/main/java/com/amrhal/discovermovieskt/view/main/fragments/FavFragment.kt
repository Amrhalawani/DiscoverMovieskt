package com.amrhal.discovermovieskt.view.main.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.amrhal.discovermovieskt.R
import com.amrhal.discovermovieskt.domain.entities.FavMovie
import kotlinx.android.synthetic.main.frag_fav_movies.view.*
import kotlin.random.Random


class FavFragment : Fragment() {

    private lateinit var favViewModel: FavFragmentVM
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.frag_fav_movies, container, false)
        favViewModel = ViewModelProviders.of(this).get(FavFragmentVM::class.java)


        rootView.button3.setOnClickListener {

            insert()
        }
        return rootView
    }


    override fun onResume() {
        super.onResume()
        getAllFavMoviesfromViewModel()
    }
    private fun getAllFavMoviesfromViewModel() {

        favViewModel.favObservable!!.observe(this, Observer { words ->

            words?.let {

                Toast.makeText(activity?.applicationContext, "List size= ${it.size}", Toast.LENGTH_SHORT).show()
            }
        })


    }

    private fun insert() {
        val number = Random.nextInt(200)
        val newMovie = FavMovie(true,number,36.0,"","","sad",false,1.5)
        favViewModel.insert(newMovie)
    }


}