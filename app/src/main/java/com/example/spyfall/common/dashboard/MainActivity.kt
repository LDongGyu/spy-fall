package com.example.spyfall.common.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spyfall.R
import com.example.spyfall.common.game.GameSettingActivity
import com.example.spyfall.common.guide.GuideActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        game_btn.setOnClickListener(gameBtnClickListener)
        tutorial_btn.setOnClickListener(tutorialBtnClickListener)
        placeList.layoutManager =  LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        placeList.adapter = PlaceListAdapter(PlaceInfo.placeData)
    }

    private val gameBtnClickListener: View.OnClickListener = View.OnClickListener {
        startActivity(Intent(this,
            GameSettingActivity::class.java))
        overridePendingTransition(R.animator.fade_in, R.animator.fade_out);
    }

    private val tutorialBtnClickListener: View.OnClickListener = View.OnClickListener {
        startActivity(Intent(this, GuideActivity::class.java))
    }

}
