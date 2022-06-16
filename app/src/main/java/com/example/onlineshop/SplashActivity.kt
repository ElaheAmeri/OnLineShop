package com.example.onlineshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        val splash=findViewById<ImageView>(R.id.imageView)
        splash.alpha=0f
        splash.animate().setDuration(1500).alpha(1f).withEndAction{
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(androidx.appcompat.R.anim.abc_fade_in,
                androidx.appcompat.R.anim.abc_fade_out)
            finish()
        }
    }
}