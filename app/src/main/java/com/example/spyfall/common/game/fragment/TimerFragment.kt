package com.example.spyfall.common.game.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spyfall.R
import kotlinx.android.synthetic.main.fragment_timer.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TimerFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TimerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TimerFragment() : Fragment() {

    private var total: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            total = it.getLong("time")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_timer, container, false)
        var downTimer = object : CountDownTimer(total * 1000,1000){
            override fun onFinish() {
                view.timer.text = "Finish!"
            }

            override fun onTick(p0: Long) {
                Log.d("timer",(p0/1000).toString())
                var time = p0/1000
                var min = (time - time%60)/60
                var second = time%60
                var minText = ""
                var secondText = ""

                if(min < 10)  minText = String.format("0%d",min)
                else minText = min.toString()

                if(second < 10) secondText = String.format("0%d",second)
                else secondText = second.toString()

                view.timer.text = String.format("%s:%s",minText,secondText)

            }

        }

        downTimer.start()
        return view
    }
}
