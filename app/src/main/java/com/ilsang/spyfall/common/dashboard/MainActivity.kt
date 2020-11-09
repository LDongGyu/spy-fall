package com.ilsang.spyfall.common.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilsang.spyfall.R
import com.ilsang.spyfall.common.game.GameSettingActivity
import com.ilsang.spyfall.common.guide.GuideActivity
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
