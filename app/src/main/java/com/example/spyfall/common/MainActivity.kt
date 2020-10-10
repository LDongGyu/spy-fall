package com.example.spyfall.common

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spyfall.R
import com.example.spyfall.common.Tutorial.TutorialActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        game_btn.setOnClickListener {
            startActivity(Intent(this,GameSettingActivity::class.java))
            finish()
        }

        tutorial_btn.setOnClickListener {
            startActivity(Intent(this, TutorialActivity::class.java))
            finish()
        }
    }
}
