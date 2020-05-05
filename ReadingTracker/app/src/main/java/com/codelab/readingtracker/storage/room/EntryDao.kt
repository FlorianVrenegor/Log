package com.codelab.readingtracker.storage.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EntryDao {
    @Query("SELECT * FROM entry")
    fun getAll(): LiveData<List<EntryEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg entries: EntryEntity)

    @Delete
    fun delete(entry: EntryEntity)
}
