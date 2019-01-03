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
import kotlinx.android.synthetic.main.frag_fav_movies.*
import kotlinx.android.synthetic.main.frag_search.*


class FavFragment : Fragment() {




    companion object {

        var favfragment:FavFragment? = null
        fun getInstance():FavFragment{  //singlton pettern

            if (favfragment == null) {
                favfragment= FavFragment()

                return favfragment as FavFragment
            }
            else
                return favfragment as FavFragment
        }

        }



    private val imageUrl = "https://images.pexels.com/photos/163065/mobile-phone-android-apps-phone-163065.jpeg"

    fun loadImage(){

        // during downloading, we will see the progress bar and after it finished progress bar will become invisible

        progressBar3.visibility = View.VISIBLE
//        GlideApp.with(activity!!).asBitmap()
//            .load(Uri.parse(imageUrl))
//            .into(object: BitmapImageViewTarget(secondFragmentImageView){
//                override fun onResourceReady(  // when is ready
//                    resource: Bitmap,
//                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
//                ) {
//                    super.onResourceReady(resource, transition)
//                progressBar2.visibility = View.INVISIBLE
//                }
//            })
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate( R.layout.frag_fav_movies,container,false )
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loadImage()
    }
}