package com.codelab.readingtracker.timer

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

import com.codelab.readingtracker.R

import java.util.concurrent.atomic.AtomicReference

class TimerFragment : Fragment() {

    private var timerTextView: TextView? = null
    private var startButton: Button? = null
    private var stopButton: Button? = null

    private var running = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_timer, container, false)

        timerTextView = view.findViewById(R.id.timer_textView)
        startButton = view.findViewById(R.id.start_btn)
        stopButton = view.findViewById(R.id.stop_btn)

        val timer = AtomicReference<CountDownTimer>()

        startButton!!.setOnClickListener {
            if (!running) {
                timer.set(object : CountDownTimer(30000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        val remaining = "Remaining: " + millisUntilFinished / 1000 + " sec"
                        timerTextView!!.text = remaining
                    }

                    override fun onFinish() {
                        val win = "You Win!"
                        timerTextView!!.text = win
                    }
                }.start())
            }
            running = true
        }

        stopButton!!.setOnClickListener {
            timer.get().cancel()
            running = false
            val canceled = "Canceled!"
            timerTextView!!.text = canceled
        }

        return view
    }
}
