package com.amrhal.movielix.view.main.fragments


import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amrhal.movielix.R
import kotlinx.android.synthetic.main.frag_info.*
import kotlinx.android.synthetic.main.frag_info.view.*


class InfoFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.frag_info, container, false)

        setupActions(rootView)
        return rootView
    }

    private fun setupActions(rootView: View) {
        rootView.view_privacy_policy_info_frag.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse (getString(R.string.privecy_policy_url))
            startActivity (openURL)
        }

    }

    override fun onResume() {
        super.onResume()
        displayAppVersion()

    }

    private fun displayAppVersion() {
        try {
            val pInfo = activity?.packageManager?.getPackageInfo(activity?.packageName,0)
            val version = pInfo?.versionName
            text_App_version_info_frag.text = "Version  $version"

        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }


}