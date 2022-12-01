package com.kurilov.deadspase.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kurilov.deadspase.data.db.entry.SemesterInfo

@Dao
interface SemesterInfoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(semesterInfo: SemesterInfo): Long

    @Query("SELECT * FROM ${SemesterInfo.TABLE_NAME} WHERE ${SemesterInfo.COLUMN_ID} == 0")
    suspend fun get() : SemesterInfo

}