package com.amrhal.discovermovieskt.view.main.fragments

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.amrhal.discovermovieskt.R
import com.amrhal.discovermovieskt.util.GlideApp

import com.bumptech.glide.request.target.BitmapImageViewTarget
import kotlinx.android.synthetic.main.frag_search.*

@SuppressLint("ValidFragment")
object SearchFragment : Fragment() {

//    companion object {
//
//        var searchFragment:SearchFragment? = null
//        fun getInstance():SearchFragment{
//
//            if (searchFragment == null) { //singlton pettern
//                searchFragment= SearchFragment()
//
//                return searchFragment as SearchFragment
//            }
//            else
//                return searchFragment as SearchFragment
//        }
//
//    }



    private val imageUrl = "https://images.pexels.com/photos/163065/mobile-phone-android-apps-phone-163065.jpeg"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate( R.layout.frag_search,container,false )
        return view
    }


}