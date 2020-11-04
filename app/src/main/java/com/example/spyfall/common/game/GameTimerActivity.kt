package com.example.spyfall.common.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.spyfall.R
import com.example.spyfall.common.dashboard.MainActivity
import com.example.spyfall.common.game.fragment.TimerFragment
import kotlinx.android.synthetic.main.activity_game_timer.*
import kotlinx.android.synthetic.main.fragment_result.*

class GameTimerActivity : AppCompatActivity() {

    private var version = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_timer)

        var totalTime = intent.getIntExtra("time",480).toLong()
        var fragmentManager = supportFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()
        var fragment = TimerFragment()
        var bundle = Bundle()
        bundle.putLong("time",totalTime)
        fragment.arguments = bundle

        fragmentTransaction.add(R.id.info,fragment)
        fragmentTransaction.commit()

        openBtn.setOnClickListener(openBtnClickListener)
    }

    val openBtnClickListener: View.OnClickListener = View.OnClickListener {
        if(version == 0) {
            answer.text = "스파이는 ${intent.getIntExtra("spy", 0)}번이었습니다."
            openBtn.text = "메인으로"
            version++
        }
        else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}
