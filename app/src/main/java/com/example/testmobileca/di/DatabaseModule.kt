package com.example.testmobileca.di

import android.content.Context
import com.example.testmobileca.data.db.Database
import com.example.testmobileca.data.db.DatabaseBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun databaseProvider(context: Context): Database {
        return DatabaseBuilder.getDatabase(context)
    }
}
