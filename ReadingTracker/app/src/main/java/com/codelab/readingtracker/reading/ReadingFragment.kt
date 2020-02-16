package com.codelab.readingtracker.reading

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.codelab.readingtracker.R

import java.util.ArrayList

class ReadingFragment : Fragment() {

    private var entries: MutableList<Entry>? = null
    private var entryProvider: EntryProvider? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_reading, container, false)

        entryProvider = context?.let { SharedPreferencesEntryProvider(it) }

        load()

        if (entries == null) {
            entries = ArrayList()
        }

        val titleEditText = view.findViewById<EditText>(R.id.title_editText)
        val pageNumberEditText = view.findViewById<EditText>(R.id.pagenumber_editText)

        val button = view.findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val title = titleEditText.text.toString()
            val pageNumberString = pageNumberEditText.text.toString()
            var pageNumber = 0
            if (pageNumberString.isNotEmpty()) {
                pageNumber = Integer.parseInt(pageNumberString)
            }
            val entry = Entry(title, pageNumber)
            entries!!.add(entry)
        }

        return view
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
        entryProvider!!.save(entries)
        Log.d("ReadingFragment Save - ", "Saved " + entries!!.toTypedArray().contentToString())
    }

    private fun load() {
        entries = entryProvider!!.load() ?: ArrayList()
        Log.d("ReadingFragment Load - ", "Loaded " + entries!!.toTypedArray().contentToString())
    }
}

