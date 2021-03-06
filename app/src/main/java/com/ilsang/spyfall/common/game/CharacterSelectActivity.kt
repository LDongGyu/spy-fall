package com.ilsang.spyfall.common.game

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import com.ilsang.spyfall.R
import com.ilsang.spyfall.database.GameInfoDataBase
import kotlinx.android.synthetic.main.activity_character_select.*
import kotlin.random.Random

class CharacterSelectActivity : AppCompatActivity() {

    lateinit var front_anim: AnimatorSet
    lateinit var back_anim: AnimatorSet
    var isFront = true
    var player = 0
    lateinit var characterArr: Array<String>
    var playerNum = 0
    var placeName = ""
    var time = 0
    var spyNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_select)

        time = intent.getIntExtra("time",480)
        playerNum = intent.getStringExtra("player").toInt()
        // DB에서 랜덤으로 주제 가져오기
        // 주제에서 랜덤으로 명수만큼 가져오기

        var charArr = mutableListOf<String>()
        characterArr = Array<String>(playerNum.toInt(),{i->""})
        val dbRead = Runnable {
            var db = GameInfoDataBase.getInstance(this)
            var placeArr = db?.placeDao()?.loadAllPlaces()
            var placeRandom = Random.nextInt(placeArr!!.size)+1
            placeName = db?.placeDao()?.findPlaceWithID(placeRandom) ?: "none"
            charArr = db?.characterDao()?.findCharacterWithID(placeRandom)?.toMutableList() ?: mutableListOf()
            characterSet(charArr, playerNum)
        }
        val dbThread = Thread(dbRead)
        dbThread.start()

        val scale = applicationContext.resources.displayMetrics.density
        card_front.cameraDistance = 8000 * scale
        card_back.cameraDistance = 8000 * scale

        front_anim = AnimatorInflater.loadAnimator(applicationContext,R.animator.front_animator) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(applicationContext,R.animator.back_animator) as AnimatorSet

        flip_btn.setOnClickListener(flipBtnClickListener)
    }

    val flipBtnClickListener: View.OnClickListener = View.OnClickListener {

        if(player == playerNum){
            GameInfo.time = time
            GameInfo.spy = spyNum
            GameInfo.place = placeName
            startActivity(Intent(this,GameTimerActivity::class.java))
            finish()
        }

        if(isFront){
            front_anim.setTarget(card_front)
            back_anim.setTarget(card_back)
            front_anim.start()
            back_anim.start()
            isFront = false
            job.text = characterArr[player]
            if(job.text == "Spy") place.text = ""
            else place.text = placeName

            if(player == playerNum-1){
                prompt.text = "아래 '시작' 버튼을 누르면\n타이머가 시작됩니다."
                flip_btn.text = "시작"
            }
            player++
        }
        else{
            front_anim.setTarget(card_back)
            back_anim.setTarget(card_front)
            back_anim.start()
            front_anim.start()
            isFront = true
        }

        flip_btn.visibility=View.GONE
        Handler().postDelayed({
            flip_btn.visibility=View.VISIBLE
        },1000)
    }

    fun characterSet(list: MutableList<String>, num: Int){
        spyNum = Random.nextInt(num)
        Log.d("selectChar","num : ${num}, spy : ${spyNum}")
        for(i in 0..num-1){
            var nowRand = Random.nextInt(list.size)
            Log.d("selectChar","list Size : ${list.size}, rand : ${nowRand}")
            if(i != spyNum){
                characterArr[i] = list[nowRand]
                list.removeAt(nowRand)
            }
            else{
                characterArr[i] = "Spy"
            }
        }
    }
}
