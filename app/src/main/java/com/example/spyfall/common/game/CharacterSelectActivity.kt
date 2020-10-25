package com.example.spyfall.common.game

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import com.example.spyfall.R
import com.example.spyfall.database.GameInfoDataBase
import kotlinx.android.synthetic.main.activity_character_select.*
import kotlin.random.Random

class CharacterSelectActivity : AppCompatActivity() {

    lateinit var front_anim: AnimatorSet
    lateinit var back_anim: AnimatorSet
    var isFront = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_select)

        var playerNum = intent.getStringExtra("player") ?: "4"
        var time = intent.getStringExtra("time") ?: "0800"

        // DB에서 랜덤으로 주제 가져오기
        // 주제에서 랜덤으로 명수만큼 가져오기
        var placeName = ""
        var charArr = arrayOf<String>()
        val dbRead = Runnable {
            var db = GameInfoDataBase.getInstance(this)
            var placeArr = db?.placeDao()?.loadAllPlaces()
            var placeRandom = Random.nextInt(placeArr!!.size)+1
            placeName = db?.placeDao()?.findPlaceWithID(placeRandom) ?: "none"
            charArr = db?.characterDao()?.findCharacterWithID(placeRandom) ?: arrayOf()
        }
        val dbThread = Thread(dbRead)
        dbThread.start()

        val scale = applicationContext.resources.displayMetrics.density
        card_front.cameraDistance = 8000 * scale
        card_back.cameraDistance = 8000 * scale

        front_anim = AnimatorInflater.loadAnimator(applicationContext,R.animator.front_animator) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(applicationContext,R.animator.back_animator) as AnimatorSet

        flip_btn.setOnClickListener {
            if(isFront){
                front_anim.setTarget(card_front)
                back_anim.setTarget(card_back)
                front_anim.start()
                back_anim.start()
                isFront = false
            }
            else{
                front_anim.setTarget(card_back)
                back_anim.setTarget(card_front)
                back_anim.start()
                front_anim.start()
                isFront = true
            }
        }
    }
}
