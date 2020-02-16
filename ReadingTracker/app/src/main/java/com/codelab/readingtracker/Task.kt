package com.codelab.readingtracker

import java.util.*

data class Task(
        val task: String,
        val done: Boolean,
        val dueDate: Date? = null
)

class Story(val title: String, val subTasks: List<Task>) {

    fun isDone() : Boolean {
        for(task in subTasks) {
            if (!task.done) {
                return false
            }
        }
        return true
    }
}