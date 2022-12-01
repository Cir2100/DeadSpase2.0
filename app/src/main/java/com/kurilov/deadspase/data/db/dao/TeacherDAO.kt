package com.kurilov.deadspase.data.db.dao

import androidx.room.*
import com.kurilov.deadspase.data.db.entry.Teacher
import kotlinx.coroutines.flow.Flow

@Dao
interface TeacherDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(groups: List<Teacher>): List<Long>

    @Query("SELECT * FROM ${Teacher.TABLE_NAME}")
    suspend fun getAll() : List<Teacher>

    @Query("SELECT * FROM ${Teacher.TABLE_NAME}")
    fun getAllFLow() : Flow<List<Teacher>>

    @Query("SELECT ${Teacher.COLUMN_ID} FROM ${Teacher.TABLE_NAME} WHERE ${Teacher.COLUMN_NAME} == :name")
    fun getIdByName(name: String) : Long?

    @Query("DELETE FROM ${Teacher.TABLE_NAME}")
    suspend fun deleteAll()

    @Transaction
    suspend fun replaceAllWhenUpdateSemester(groups: List<Teacher>) {
        deleteAll()
        insertAll(groups)
    }

}