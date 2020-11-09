package com.example.spyfall.common.guide

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import com.example.spyfall.R
import com.example.spyfall.common.dashboard.MainActivity
import com.example.spyfall.common.dashboard.PlaceItem
import com.example.spyfall.common.guide.fragment.*
import kotlinx.android.synthetic.main.activity_guide.*

class GuideActivity : AppCompatActivity() {

    var page = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)

        var fragment0 = Guide0Fragment()
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.animator.slide_in,R.animator.slide_out)
        fragmentTransaction.add(R.id.guideLayout,fragment0)
        fragmentTransaction.commit()

        nextBtn.setOnClickListener(nextBtnOnClickListener)
    }

    val nextBtnOnClickListener: View.OnClickListener = View.OnClickListener {

        var fragmentTransaction = supportFragmentManager.beginTransaction()
        var fragment = Fragment()

        when(page){
            0 -> fragment = Guide1Fragment()
            1 -> fragment = Guide2Fragment()
            2 -> fragment = Guide3Fragment()
            3 -> fragment = Guide4Fragment()
            4 -> {
                fragment = Guide5Fragment()
                nextBtn.text = "메인으로"
            }
            5 -> {
                startActivity(Intent(applicationContext,MainActivity::class.java))
                finish()
            }
        }
        fragmentTransaction.setCustomAnimations(R.animator.slide_in,R.animator.slide_out)
        fragmentTransaction.replace(R.id.guideLayout,fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
        page++
    }
}