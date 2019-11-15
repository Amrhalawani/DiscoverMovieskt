package com.amrhal.movielix.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.amrhal.movielix.R
import com.amrhal.movielix.domain.core.Constants.currentYear
import com.amrhal.movielix.view.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*
import java.util.*
import androidx.core.app.ActivityOptionsCompat


class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        currentYear = Calendar.getInstance().get(Calendar.YEAR)

        val arr = arrayListOf<Int>(R.drawable.pic_1, R.drawable.pic_2, R.drawable.pic_3)

        val randomNum = (0 until arr.size).shuffled().first()
        image_background_splash.setImageResource(arr[randomNum])


        Handler().postDelayed(
            {
                val i = Intent(this, MainActivity::class.java)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this,
                        image_logo_splash,
                        getString(R.string.logo)
                    )
                    startActivity(i, options.toBundle())
                } else {
                    startActivity(i)
                }

                finish()
            }, 3000
        )
    }
}
