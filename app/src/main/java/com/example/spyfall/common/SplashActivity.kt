package com.example.spyfall.common

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.spyfall.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var sharedPreferences = getSharedPreferences("isFirst", MODE_PRIVATE)

        Handler().postDelayed({
            if(sharedPreferences.getBoolean("first",true)){
                sharedPreferences.edit().putBoolean("first",false).commit()
                startActivity(Intent(this, TutorialActivity::class.java))
                finish()
            }
            else{
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        },2000)
    }
}
