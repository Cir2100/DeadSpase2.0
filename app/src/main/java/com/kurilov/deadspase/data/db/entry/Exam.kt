package com.kurilov.deadspase.data.db.entry

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@kotlinx.serialization.Serializable
@Entity(tableName =  Exam.TABLE_NAME)
data class Exam(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: Long,
    @ColumnInfo(name = COLUMN_DATE)
    val date : String,
    @ColumnInfo(name = COLUMN_CHANGE)
    val change : String,
    @ColumnInfo(name = COLUMN_BUILD)
    val build: String,
    @ColumnInfo(name = COLUMN_ROOM)
    val room: String,
    @ColumnInfo(name = COLUMN_DISC)
    val disc: String,
    @ColumnInfo(name = COLUMN_GROUPS)
    val groups: String,
    @ColumnInfo(name = COLUMN_TEACHERS)
    val teachers: String,
) {
    companion object {
        const val TABLE_NAME = "Exam"

        const val COLUMN_ID = "id"
        const val COLUMN_DATE = "date"
        const val COLUMN_CHANGE = "change"
        const val COLUMN_BUILD = "build"
        const val COLUMN_ROOM = "room"
        const val COLUMN_DISC = "disc"
        const val COLUMN_GROUPS = "groups"
        const val COLUMN_TEACHERS = "teachers"
    }
}
