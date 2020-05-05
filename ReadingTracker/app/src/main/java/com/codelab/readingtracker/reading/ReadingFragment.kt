package com.codelab.readingtracker.reading

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codelab.readingtracker.R
import com.codelab.readingtracker.storage.EntryProvider
import com.codelab.readingtracker.storage.SharedPreferencesEntryProvider
import kotlinx.android.synthetic.main.fragment_reading.*
import java.util.*

class ReadingFragment : Fragment() {

    private var entries: ArrayList<Entry> = ArrayList()
    private lateinit var entryProvider: EntryProvider

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_reading, container, false)

        entryProvider = SharedPreferencesEntryProvider(requireContext())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        button.setOnClickListener {
            entries.add(Entry(getTitle(), getPageNumber()))
        }

        load()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun getTitle() : String {
        return title_editText.text.toString()
    }

    private fun getPageNumber() : Int {
        val pageNumberString = pagenumber_editText.text.toString()

        if (pageNumberString.isNotEmpty()) {
            return Integer.parseInt(pageNumberString)
        }
        return 0
    }

    override fun onResume() {
        super.onResume()
        load()
    }

    override fun onPause() {
        super.onPause()
        save()
    }

    override fun onStop() {
        super.onStop()
        save()
    }

    private fun save() {
        entryProvider.save(entries)
        Log.d("ReadingFragment Save - ", "Saved " + entries.toTypedArray().contentToString())
    }

    private fun load() {
        entries = entryProvider.load()
        Log.d("ReadingFragment Load - ", "Loaded " + entries.toTypedArray().contentToString())
    }
}

