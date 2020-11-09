package com.ilsang.spyfall.common

import android.content.Intent
import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ilsang.spyfall.R
import com.ilsang.spyfall.common.dashboard.MainActivity
import com.ilsang.spyfall.common.dashboard.PlaceInfo
import com.ilsang.spyfall.common.dashboard.PlaceItem
import com.ilsang.spyfall.common.guide.GuideActivity
import com.ilsang.spyfall.database.GameInfoDataBase
import com.ilsang.spyfall.database.place.PlaceMap

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
