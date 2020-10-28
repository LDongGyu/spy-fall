package com.example.spyfall.common.Tutorial

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spyfall.R
import com.example.spyfall.common.MainActivity
import kotlinx.android.synthetic.main.fragment_screen_slide_page.view.*

class TutorialSlidePageFragment(position: Int) : Fragment() {
    val position = position

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        var view = inflater.inflate(R.layout.fragment_screen_slide_page, container, false)
        view.txt.text = position.toString()

        when(position){
            0 -> view.setBackgroundColor(Color.parseColor("#d9598c"))
            1 -> view.setBackgroundColor(Color.parseColor("#db706c"))
            2 -> view.setBackgroundColor(Color.parseColor("#f1d2e7"))
            3 -> {
                view.setBackgroundColor(Color.parseColor("#bbb0dc"))
                view.btn.visibility = View.VISIBLE
                view.btn.setOnClickListener {
                    startActivity(Intent(context, MainActivity::class.java))
                }
            }
        }

        return view
    }
}
