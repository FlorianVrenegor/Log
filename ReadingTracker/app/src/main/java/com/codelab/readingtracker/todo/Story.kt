package com.codelab.readingtracker.todo

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