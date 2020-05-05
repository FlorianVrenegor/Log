package com.codelab.readingtracker.todo

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.codelab.readingtracker.R
import kotlinx.android.synthetic.main.view_todo_item.view.*

class TodoItemView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttrs: Int = 0
) : LinearLayout(context, attrs, defStyleAttrs) {

    init {
        inflate(context, R.layout.view_todo_item, this)
    }

    fun setTask(task: String) {
        if (todo_item_textView != null) {
            todo_item_textView!!.text = task
        }
    }
}
