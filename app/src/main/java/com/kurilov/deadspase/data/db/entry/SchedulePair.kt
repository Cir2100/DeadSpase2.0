package com.kurilov.deadspase.data.db.entry

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
@Entity(tableName = SchedulePair.TABLE_NAME)
data class SchedulePair(
    @SerializedName("ItemId")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    val id: Long,
    @ColumnInfo(name = COLUMN_GROUP_NAME)
    val groupName: String? = null,
    @SerializedName("Week")
    @ColumnInfo(name = COLUMN_WEEK)
    val week: Int,
    @SerializedName("Day")
    @ColumnInfo(name = COLUMN_DAY)
    val day: Int,
    @SerializedName("Less")
    @ColumnInfo(name = COLUMN_LESS)
    val less: Int,
    @SerializedName("Build")
    @ColumnInfo(name = COLUMN_BUILD)
    val build: String,
    @SerializedName("Rooms")
    @ColumnInfo(name = COLUMN_ROOM)
    val room: String,
    @SerializedName("Disc")
    @ColumnInfo(name = COLUMN_DISC)
    val disc: String,
    @SerializedName("Type")
    @ColumnInfo(name = COLUMN_TYPE)
    val type: String,
    @SerializedName("GroupsText")
    @ColumnInfo(name = COLUMN_GROUPS)
    val groups: String,
    @SerializedName("PrepsText")
    @ColumnInfo(name = COLUMN_TEACHERS)
    val teachers: String,
    @ColumnInfo(name = COLUMN_IS_INTERNET_CASH)
    val isInternetCash: Boolean = true,
) {
    companion object {
        const val TABLE_NAME = "SchedulePair"

        const val COLUMN_ID = "id"
        const val COLUMN_GROUP_NAME = "group_name"
        const val COLUMN_WEEK = "week"
        const val COLUMN_DAY = "day"
        const val COLUMN_LESS = "less"
        const val COLUMN_BUILD = "build"
        const val COLUMN_ROOM = "room"
        const val COLUMN_DISC = "disc"
        const val COLUMN_TYPE = "type"
        const val COLUMN_GROUPS = "groups"
        const val COLUMN_TEACHERS = "teachers"
        const val COLUMN_IS_INTERNET_CASH = "is_internet_cash"
    }
}
