package com.codelab.readingtracker.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.codelab.readingtracker.R
import kotlinx.android.synthetic.main.fragment_todo.*

class TodoFragment : Fragment() {

    private var counter = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add_button.setOnClickListener {
            val todoItem = TodoItemView(requireContext())
            val tv = TextView(context)
            tv.text = if (todo_editText.text.isNotEmpty()) {
                todoItem.setTask(todo_editText.text.toString())
                todo_editText.text
            } else {
                Toast.makeText(context, "Todo is empty. Creating generic Item.", Toast.LENGTH_SHORT).show()
                todoItem.setTask("Entry $counter")
                "Entry $counter"
            }
            counter++
            todo_linearLayout.addView(todoItem)

            todo_editText.setText("")
        }
    }
}
