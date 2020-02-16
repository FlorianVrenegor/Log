package com.codelab.readingtracker

import java.util.*

data class Entry(
        val date: Date,
        val book: Book,
        val currentPage: Int
)

data class Book(
        val title: String,
        val numberOfPages: Int
)