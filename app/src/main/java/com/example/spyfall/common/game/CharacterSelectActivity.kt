package com.example.spyfall.common.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import com.example.spyfall.R
import com.example.spyfall.database.GameInfoDataBase
import kotlin.random.Random

class CharacterSelectActivity : AppCompatActivity() {

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
    }
}
