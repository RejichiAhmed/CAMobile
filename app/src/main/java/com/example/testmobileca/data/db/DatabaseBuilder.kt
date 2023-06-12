package com.example.testmobileca.data.db

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {
    fun getDatabase(context: Context): Database {

        return Room.databaseBuilder(context, Database::class.java, "database")
            .build()
    }
}
