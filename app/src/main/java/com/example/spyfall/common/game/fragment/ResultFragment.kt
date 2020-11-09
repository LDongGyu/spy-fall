package com.example.spyfall.common.game.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spyfall.R
import com.example.spyfall.common.game.GameInfo
import kotlinx.android.synthetic.main.fragment_result.view.*

class ResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_result, container, false)
        view.placeAnswer.text = GameInfo.place
        view.spyAnswer.text = "${GameInfo.spy+1}P"

        return view
    }
}
