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
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var placeData = mutableListOf<PlaceItem>()
    var placeMap = mapOf("아이즈원" to R.drawable.izone, "학교" to R.drawable.school, "영화관" to R.drawable.movie, "코다차야" to R.drawable.pub)

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
