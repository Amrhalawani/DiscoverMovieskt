package com.amrhal.discovermovieskt.view.main.fragments

import android.annotation.SuppressLint

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.amrhal.discovermovieskt.R


@SuppressLint("ValidFragment")
object SearchFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate( R.layout.frag_search,container,false )
        return view
    }


}