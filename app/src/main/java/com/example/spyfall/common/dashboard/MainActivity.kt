package com.example.spyfall.common.dashboard

import android.content.Intent
import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spyfall.R
import com.example.spyfall.common.tutorial.TutorialActivity
import com.example.spyfall.common.game.GameSettingActivity
import com.example.spyfall.database.GameInfoDataBase
import com.example.spyfall.database.place.Place
import com.example.spyfall.database.place.PlaceMap.placeMap
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var placeData = mutableListOf<PlaceItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        game_btn.setOnClickListener(gameBtnClickListener)
        tutorial_btn.setOnClickListener(tutorialBtnClickListener)
        placeList.layoutManager =  LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        getPlaceData()
        placeList.adapter = PlaceListAdapter(placeData)
    }

    private val gameBtnClickListener: View.OnClickListener = View.OnClickListener {
        startActivity(Intent(this,
            GameSettingActivity::class.java))
        overridePendingTransition(R.animator.fade_in, R.animator.fade_out);
    }

    private val tutorialBtnClickListener: View.OnClickListener = View.OnClickListener {
        startActivity(Intent(this, TutorialActivity::class.java))
    }

    private fun getPlaceData(){
        val dbRead = Runnable {
            var db = GameInfoDataBase.getInstance(this)
            var placeAll = db?.placeDao()?.loadAllPlaces()
            placeAll?.forEach {
                placeData.add(PlaceItem(Icon.createWithResource(this,placeMap[it.placeName]?:R.drawable.spyblack),it.placeName))
            }
        }
        val dbThread = Thread(dbRead)
        dbThread.start()
    }
}