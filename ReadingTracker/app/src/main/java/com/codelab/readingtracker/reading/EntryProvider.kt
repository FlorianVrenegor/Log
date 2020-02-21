package com.codelab.readingtracker.reading

interface EntryProvider {
    fun save(entries: List<Entry>)
    fun load(): List<Entry>
}
