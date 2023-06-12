package com.example.testmobileca.data.db

import androidx.room.RoomDatabase
import com.example.testmobileca.data.model.Operation


@androidx.room.Database(entities = [Operation::class], version = 1,exportSchema = false)
abstract class Database : RoomDatabase() {
}
