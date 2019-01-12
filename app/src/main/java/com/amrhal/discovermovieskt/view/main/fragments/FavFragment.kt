package com.amrhal.discovermovieskt.view.main.fragments

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amrhal.discovermovieskt.R
import com.amrhal.discovermovieskt.util.GlideApp

import com.bumptech.glide.request.target.BitmapImageViewTarget
import kotlinx.android.synthetic.main.frag_fav_movies.*
import kotlinx.android.synthetic.main.frag_search.*


@SuppressLint("ValidFragment")
object FavFragment : Fragment() {


//
//    companion object {
//
//        var favfragment:FavFragment? = null
//        fun getInstance():FavFragment{  //singleton pattern
//
//            if (favfragment == null) {
//                favfragment= FavFragment()
//
//                return favfragment as FavFragment
//            }
//            else
//                return favfragment as FavFragment
//        }
//
//        }
//

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate( R.layout.frag_fav_movies,container,false )
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}