package com.amrhal.discovermovieskt.view.main.fragments

import android.annotation.SuppressLint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amrhal.discovermovieskt.R



@SuppressLint("ValidFragment")
object FavFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate( R.layout.frag_fav_movies,container,false )
        return view
    }


}