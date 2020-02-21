package com.codelab.readingtracker.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entry")
data class EntryEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    val title: String,
    val page: Int?
)