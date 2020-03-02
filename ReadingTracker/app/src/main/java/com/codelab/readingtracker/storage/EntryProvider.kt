package com.codelab.readingtracker.storage

import com.codelab.readingtracker.reading.Entry

interface EntryProvider {
    fun save(entries: List<Entry>)
    fun load(): ArrayList<Entry>
}
