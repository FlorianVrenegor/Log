package com.codelab.readingtracker.todo;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.codelab.readingtracker.R;

public class TodoItemView extends View {

    TextView taskTextView;

    public TodoItemView(Context context) {
        super(context);
        inflate(context, R.layout.view_todo_item, null);
        taskTextView = findViewById(R.id.todo_item_textView);
    }

    public void setTask(String task) {
        if(taskTextView != null) {
            taskTextView.setText(task);
        }
    }
}
