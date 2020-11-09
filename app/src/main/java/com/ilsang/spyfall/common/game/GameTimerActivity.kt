package com.ilsang.spyfall.common.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ilsang.spyfall.R
import com.ilsang.spyfall.common.dashboard.MainActivity
import com.ilsang.spyfall.common.game.fragment.ResultFragment
import com.ilsang.spyfall.common.game.fragment.TimerFragment
import kotlinx.android.synthetic.main.activity_game_timer.*

class GameTimerActivity : AppCompatActivity() {

    private var version = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_timer)

        var totalTime = GameInfo.time

        var fragment = TimerFragment()
        var bundle = Bundle()
        bundle.putInt("time",totalTime)
        fragment.arguments = bundle

        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.info,fragment)
        fragmentTransaction.commit()

        openBtn.setOnClickListener(openBtnClickListener)
    }

    val openBtnClickListener: View.OnClickListener = View.OnClickListener {
        if(version == 0) {
            var spy = intent.getIntExtra("spy",1)
            var place =  intent.getStringExtra("place")
            var bundle = Bundle()
            bundle.putInt("spy",spy)
            bundle.putString("place",place)

            var fragment = ResultFragment()
            fragment.arguments = bundle
            var fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.info,fragment)
            fragmentTransaction.commit()
            openBtn.text = "메인으로"
            version++
        }
        else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}
