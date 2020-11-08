package com.example.spyfall.common

import android.content.Intent
import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.spyfall.R
import com.example.spyfall.common.dashboard.MainActivity
import com.example.spyfall.common.dashboard.PlaceInfo
import com.example.spyfall.common.dashboard.PlaceItem
import com.example.spyfall.common.guide.GuideActivity
import com.example.spyfall.database.GameInfoDataBase
import com.example.spyfall.database.place.PlaceMap

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var sharedPreferences = getSharedPreferences("isFirst", MODE_PRIVATE)

        getPlaceData()

        Handler().postDelayed({
            var intent = Intent(this, MainActivity::class.java)

            if(sharedPreferences.getBoolean("first",true)){
                sharedPreferences.edit().putBoolean("first",false).commit()
                intent = Intent(this, GuideActivity::class.java)
            }
            startActivity(intent)
            finish()
        },2000)
    }

    private fun getPlaceData() {
        val dbRead = Runnable {
            var db = GameInfoDataBase.getInstance(applicationContext)
            var placeAll = db?.placeDao()?.loadAllPlaces()
            placeAll?.forEach {
                PlaceInfo.placeData.add(PlaceItem(Icon.createWithResource(applicationContext,
                    PlaceMap.placeMap[it.placeName]?:R.drawable.spyblack),it.placeName))
            }
        }
        val dbThread = Thread(dbRead)
        dbThread.start()
    }
}
