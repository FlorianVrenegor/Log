package com.codelab.readingtracker

import android.os.Bundle
import android.widget.Button

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

import com.codelab.readingtracker.reading.ReadingFragment
import com.codelab.readingtracker.timer.TimerFragment
import com.codelab.readingtracker.todo.TodoFragment

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: FragmentAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = FragmentAdapter(supportFragmentManager, 1)

        viewPager = findViewById(R.id.container)
        setupViewPager(viewPager)

        val readingButton = findViewById<Button>(R.id.reading_button)
        readingButton.setOnClickListener { setTab(0) }

        val timerButton = findViewById<Button>(R.id.timer_button)
        timerButton.setOnClickListener { setTab(1) }

        val todoButton = findViewById<Button>(R.id.todo_button)
        todoButton.setOnClickListener { setTab(2) }
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = FragmentAdapter(supportFragmentManager, 1)
        adapter.addFragment(ReadingFragment())
        adapter.addFragment(TimerFragment())
        adapter.addFragment(TodoFragment())
        viewPager.adapter = adapter
    }

    fun setTab(index: Int) {
        viewPager.currentItem = index
    }
}
