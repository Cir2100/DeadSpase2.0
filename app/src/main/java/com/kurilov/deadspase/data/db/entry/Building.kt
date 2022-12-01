package com.kurilov.deadspase.data.db.entry

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
@Entity(tableName = Building.TABLE_NAME)
data class Building(
    @SerializedName("ItemId")
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: Long,
    @SerializedName("Name")
    @ColumnInfo(name = COLUMN_NAME)
    val name: Boolean,
    @SerializedName("Title")
    @ColumnInfo(name = COLUMN_TITLE)
    val title: String,
) {
    companion object {
        const val TABLE_NAME = "Building"

        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_TITLE = "title"
    }
}
