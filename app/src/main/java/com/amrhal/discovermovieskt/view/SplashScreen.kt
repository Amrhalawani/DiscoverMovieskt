package com.amrhal.discovermovieskt.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.amrhal.discovermovieskt.R
import com.amrhal.discovermovieskt.domain.core.Constants.currentYear
import com.amrhal.discovermovieskt.view.main.MainActivity
import java.util.*

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        currentYear = Calendar.getInstance().get(Calendar.YEAR)
        Handler().postDelayed({startActivity(Intent(this, MainActivity::class.java))
        finish()
        }, 2000)
    }
}
