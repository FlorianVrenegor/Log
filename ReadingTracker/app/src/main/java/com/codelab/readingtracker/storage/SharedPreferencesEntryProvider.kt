package com.codelab.readingtracker.storage

import android.content.Context
import android.content.SharedPreferences

import androidx.preference.PreferenceManager
import com.codelab.readingtracker.reading.Entry

import java.util.ArrayList
import java.util.HashSet
import java.util.Objects

class SharedPreferencesEntryProvider(context: Context) : EntryProvider {

    private val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    override fun save(entries: List<Entry>) {
        val strings = ArrayList<String>()
        entries.forEach { entry -> strings.add(entry.toString()) }
        val set = HashSet(strings)

        val editor = sharedPreferences.edit()
        editor.remove("Entries")
        editor.putStringSet("Entries", set).apply()
    }

    override fun load(): ArrayList<Entry> {
        val entries = ArrayList<Entry>()

        ArrayList(sharedPreferences.getStringSet("Entries", HashSet())!!).forEach { s -> entries.add(Entry.fromString(s)) }

        return entries
    }
}
