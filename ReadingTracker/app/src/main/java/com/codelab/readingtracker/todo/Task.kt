package com.codelab.readingtracker.todo

import java.util.*

data class Task(
        val task: String,
        val done: Boolean,
        val dueDate: Date? = null
)