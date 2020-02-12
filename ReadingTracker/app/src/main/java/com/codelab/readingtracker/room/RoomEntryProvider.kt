package com.codelab.readingtracker.room

import android.content.Context
import androidx.room.Room
import com.codelab.readingtracker.Entry
import com.codelab.readingtracker.EntryProvider

class RoomEntryProvider(context: Context) : EntryProvider {

    private val db: EntryDatabase = Room.databaseBuilder(
            context,
            EntryDatabase::class.java,
            "entry"
    ).build()

    var entryDao = db.entryDao()

    override fun save(entries: List<Entry>) {
        val entities: List<EntryEntity> = entries.map { entry -> EntryEntity(1, entry.title, entry.pageNumber) }
        entryDao.insertAll(*entities.toTypedArray())
    }

    override fun load(): List<Entry>? {
        val entities: List<EntryEntity>? = entryDao.getAll().value ?: return null
        return entities!!.map { entryEntity -> Entry(entryEntity.title, entryEntity.page ?: 0) }
    }
}
