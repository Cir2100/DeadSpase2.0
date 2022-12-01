package com.kurilov.deadspase.data.db.entry

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
@Entity(tableName = SemesterInfo.TABLE_NAME)
data class SemesterInfo(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id : Long = 0,
    @SerializedName("IsAutumn")
    @ColumnInfo(name = COLUMN_IS_AUTUMN)
    val isAutumn: Boolean,
    @SerializedName("Update")
    @ColumnInfo(name = COLUMN_UPDATE)
    val update: String,
    @SerializedName("IsWeekRed")
    @ColumnInfo(name = COLUMN_IS_WEEK_RED)
    val isWeekRed: Boolean,
) {
    companion object {
        const val TABLE_NAME = "SemesterInfo"

        const val COLUMN_ID = "id"
        const val COLUMN_IS_AUTUMN = "is_autumn"
        const val COLUMN_UPDATE = "update"
        const val COLUMN_IS_WEEK_RED = "is_week_red"
    }
}