package com.xoxoer.newspocket.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xoxoer.newspocket.model.example.Example

@Database(entities = [Example::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
}