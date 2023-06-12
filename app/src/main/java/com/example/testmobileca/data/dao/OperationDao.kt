package com.example.testmobileca.data.dao


import androidx.room.*
import com.example.testmobileca.data.model.Operation
import kotlinx.coroutines.flow.Flow


@Dao
interface OperationDao {

    @Query("SELECT count(*) FROM operations")
    suspend fun getCount(): Int

    @Query("SELECT * from operations")
    fun getAllLeague(): Flow<List<Operation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg league: Operation)

    @Delete
    suspend fun delete(league: Operation)

    @Query("DELETE from operations")
    suspend fun deleteAll()

}
