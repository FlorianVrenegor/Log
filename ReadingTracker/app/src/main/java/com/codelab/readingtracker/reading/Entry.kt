package com.codelab.readingtracker.reading

class Entry(var title: String, var pageNumber: Int) {

    override fun toString(): String {
        return "$title,$pageNumber"
    }

    companion object {

        fun fromString(entry: String): Entry {
            val fields = entry.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val title = fields[0]
            val pageNumber = Integer.parseInt(fields[1])
            return Entry(title, pageNumber)
        }
    }
}
