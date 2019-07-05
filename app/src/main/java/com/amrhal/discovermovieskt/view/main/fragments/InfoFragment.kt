package com.amrhal.discovermovieskt.view.main.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amrhal.discovermovieskt.R
import kotlinx.android.synthetic.main.frag_info.view.*


@SuppressLint("ValidFragment")
object InfoFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.frag_info, container, false)

        setupActions(rootView)
        return rootView
    }

    private fun setupActions(rootView: View) {
        rootView.view_privacy_policy_info_frag.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(getString(R.string.privecy_policy_url))
            startActivity(openURL)
        }

    }

    override fun onResume() {
        super.onResume()

    }


}