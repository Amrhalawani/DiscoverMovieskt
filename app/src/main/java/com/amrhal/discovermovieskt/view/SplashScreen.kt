package com.amrhal.discovermovieskt.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.amrhal.discovermovieskt.R
import com.amrhal.discovermovieskt.view.main.MainActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({startActivity(Intent(this, MainActivity::class.java))
        finish()
        }, 2000)
    }
}
