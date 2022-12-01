package com.kurilov.deadspase.data.db.entry

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName =  Deadline.TABLE_NAME)
data class Deadline(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    val id: Long,
    @ColumnInfo(name = COLUMN_TITLE)
    val title : String,
    @ColumnInfo(name = COLUMN_DISC)
    val disc : String,
    @ColumnInfo(name = COLUMN_LAST_DATE)
    val lastDate : String,
    @ColumnInfo(name = COLUMN_IS_DONE)
    var isDone : Boolean
) {
    companion object {
        const val TABLE_NAME = "Deadline"

        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DISC = "disc"
        const val COLUMN_LAST_DATE = "last_date"
        const val COLUMN_IS_DONE = "is_done"
    }
}
