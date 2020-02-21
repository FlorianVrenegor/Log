package com.codelab.readingtracker.todo

import android.content.Context
import android.view.View
import android.widget.TextView

import com.codelab.readingtracker.R

class TodoItemView(context: Context) : View(context) {

    private var taskTextView: TextView? = null

    init {
        inflate(context, R.layout.view_todo_item, null)
        taskTextView = findViewById(R.id.todo_item_textView)
    }

    fun setTask(task: String) {
        if (taskTextView != null) {
            taskTextView!!.text = task
        }
    }
}
