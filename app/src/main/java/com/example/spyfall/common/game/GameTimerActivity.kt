package com.example.spyfall.common.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.example.spyfall.R
import kotlinx.android.synthetic.main.activity_game_timer.*

class GameTimerActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_timer)

        var totalTime = intent.getIntExtra("time",480).toLong()
        Log.d("timer",totalTime.toString())

        var downTimer = object : CountDownTimer(totalTime * 1000,1000){
            override fun onFinish() {
                timer.text = "Finish!"
            }

            override fun onTick(p0: Long) {
                Log.d("timer",(p0/1000).toString())
                var time = p0/1000
                var min = (time - time%60)/60
                var second = time%60
                var minText = ""
                var secondText = ""

                if(min < 10)  minText = String.format("0%d",min)
                else minText = min.toString()

                if(second < 10) secondText = String.format("0%d",second)
                else secondText = second.toString()

                timer.text = String.format("%s:%s",minText,secondText)
            }

        }

        downTimer.start()
    }


}
