package com.kurilov.deadspase.data.db.dao

import androidx.room.*
import com.kurilov.deadspase.data.db.entry.SchedulePair
import kotlinx.coroutines.flow.Flow

@Dao
interface SchedulePairDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(groups: List<SchedulePair>): List<Long>

    @Query("SELECT * FROM ${SchedulePair.TABLE_NAME}")
    suspend fun getAll() : List<SchedulePair>

    @Query("SELECT * FROM ${SchedulePair.TABLE_NAME}")
    fun getAllFLow() : Flow<List<SchedulePair>>

    @Query("SELECT * FROM ${SchedulePair.TABLE_NAME} WHERE ${SchedulePair.COLUMN_IS_INTERNET_CASH} == 1")
    suspend fun getAllCash() : List<SchedulePair>

    @Query("SELECT * FROM ${SchedulePair.TABLE_NAME} WHERE ${SchedulePair.COLUMN_IS_INTERNET_CASH} == 1")
    fun getAllCashFLow() : Flow<List<SchedulePair>>

    @Query("SELECT * FROM ${SchedulePair.TABLE_NAME} WHERE ${SchedulePair.COLUMN_IS_INTERNET_CASH} == 0 AND ${SchedulePair.COLUMN_GROUP_NAME} == :name")
    suspend fun getUserScheduleScheduleByName(name: String) : List<SchedulePair>

    @Query("SELECT * FROM ${SchedulePair.TABLE_NAME} WHERE ${SchedulePair.COLUMN_IS_INTERNET_CASH} == 0 AND ${SchedulePair.COLUMN_GROUP_NAME} == :name")
    fun getUserScheduleScheduleByGroupNameFLow(name: String) : Flow<List<SchedulePair>>

    @Query("DELETE FROM ${SchedulePair.TABLE_NAME} WHERE ${SchedulePair.COLUMN_GROUP_NAME} == :name")
    suspend fun deleteUserScheduleScheduleByGroupName(name: String)

}