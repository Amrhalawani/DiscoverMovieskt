package com.amrhal.discovermovieskt.view.main.fragments

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
import kotlinx.android.synthetic.main.frag_home.*

class HomeFragment : Fragment() {

    companion object {

        var homeFragment:HomeFragment? = null
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

    fun loadImage(){
        //during downloading we will see prograssbar and after finish prograssbar will become invisible

        progressBar.visibility = View.VISIBLE
//        GlideApp.with(activity!!).asBitmap()
//            .load(Uri.parse(imageUrl))
//            .into(object: BitmapImageViewTarget(firstFragmentImageView){
//                override fun onResourceReady( // when the is ready
//                    resource: Bitmap,
//                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
//                ) {
//                    super.onResourceReady(resource, transition)
//                progressBar.visibility = View.INVISIBLE
//                }
//            })
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_home,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loadImage()
    }
}