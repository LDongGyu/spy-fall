package com.example.spyfall.common.guide.fragment

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import com.example.spyfall.R

class Guide3Fragment : Fragment() {

    lateinit var aniView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        aniView = inflater.inflate(R.layout.fragment_guide3, container, false)
        var aniTest = AnimatorInflater.loadAnimator(activity,R.animator.slide_in) as AnimatorSet
        var layout = aniView.findViewById(R.id.num) as TextView
        aniTest.setTarget(layout)
        aniTest.start()

        Handler().postDelayed({
            var aniTest1 = AnimatorInflater.loadAnimator(activity,R.animator.slide_in) as AnimatorSet
            var layout = aniView.findViewById(R.id.text) as TextView
            layout.visibility = View.VISIBLE
            aniTest1.setTarget(layout)
            aniTest1.start()
        },500)
        return aniView
    }

}
