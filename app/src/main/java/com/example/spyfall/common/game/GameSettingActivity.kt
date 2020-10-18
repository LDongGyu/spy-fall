package com.example.spyfall.common.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.spyfall.R
import kotlinx.android.synthetic.main.activity_game_setting.*

class GameSettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_setting)

        minusBtn.setOnClickListener(playerMinusBtnClickListener)
        plusBtn.setOnClickListener(playerPlusBtnClickListener)
        minPlusBtn.setOnClickListener(minPlusBtnClickListener)
        minMinusBtn.setOnClickListener(minMinusBtnClickListener)
        secondPlusBtn.setOnClickListener(secondPlusBtnClickListener)
        secondMinusBtn.setOnClickListener(secondMinusBtnClickListener)
        startBtn.setOnClickListener(startBtnClickListener)
    }

    private val playerMinusBtnClickListener: View.OnClickListener = View.OnClickListener {
        var player = playerCountTxt.text.toString().toInt()
        if(player > 4){
            playerCountTxt.text = String.format("0%d",player-1)
        }
        else{
            Toast.makeText(this,"4명이 최소 인원입니다.",Toast.LENGTH_SHORT).show()
        }
    }

    private val playerPlusBtnClickListener: View.OnClickListener = View.OnClickListener {
        var player = playerCountTxt.text.toString().toInt()
        when(player){
            in 4..8 -> playerCountTxt.text = String.format("0%d",player+1)
            9 -> playerCountTxt.text = String.format("%d",player+1)
            else -> Toast.makeText(this,"10명이 최대 인원입니다.",Toast.LENGTH_SHORT).show()
        }
    }

    private val minPlusBtnClickListener: View.OnClickListener = View.OnClickListener {
        var min = minTxt.text.toString().toInt()
        if(min < 9) minTxt.text = String.format("0%d",min+1)
        else minTxt.text = String.format("%d",min+1)
    }

    private val minMinusBtnClickListener: View.OnClickListener = View.OnClickListener {
        var min = minTxt.text.toString().toInt()
        when(min) {
            0 -> minTxt.text = String.format("%d", 59)
            in 1..10 -> minTxt.text = String.format("0%d", min - 1)
            else -> minTxt.text = String.format("%d", min - 1)
        }
    }

    private val secondPlusBtnClickListener: View.OnClickListener = View.OnClickListener {
        var second = secondTxt.text.toString().toInt()
        if(second < 9) secondTxt.text = String.format("0%d",second+1)
        else secondTxt.text = String.format("%d",second+1)
    }

    private val secondMinusBtnClickListener: View.OnClickListener = View.OnClickListener {
        var second = secondTxt.text.toString().toInt()
        when (second) {
            0 -> secondTxt.text = String.format("%d", 59)
            in 1..10 -> secondTxt.text = String.format("0%d", second - 1)
            else -> secondTxt.text = String.format("%d", second - 1)
        }
    }

    private val startBtnClickListener: View.OnClickListener = View.OnClickListener {
        var intent = Intent(this, CharacterSelectActivity::class.java)
        intent.putExtra("player",playerCountTxt.text)
        intent.putExtra("time",String.format("%s%s",minTxt,secondTxt))
        startActivity(intent)
        finish()
    }
}