package com.kurilov.deadspase.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.kurilov.deadspase.data.db.entry.Group
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(groups: List<Group>): List<Long>

    @Query("SELECT * FROM ${Group.TABLE_NAME}")
    suspend fun getAll() : List<Group>

    @Query("SELECT * FROM ${Group.TABLE_NAME}")
    fun getAllFLow() : Flow<List<Group>>

    @Query("SELECT ${Group.COLUMN_ID} FROM ${Group.TABLE_NAME} WHERE ${Group.COLUMN_NAME} == :name")
    fun getIdByName(name: String) : Long?

    @Query("DELETE FROM ${Group.TABLE_NAME}")
    suspend fun deleteAll()

    @Transaction
    suspend fun replaceAllWhenUpdateSemester(groups: List<Group>) {
        deleteAll()
        insertAll(groups)
    }

}