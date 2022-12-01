package com.kurilov.deadspase.data.db.entry

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
@Entity(tableName = Group.TABLE_NAME)
data class Group(
    @SerializedName("ItemId")
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: Long,
    @SerializedName("Name")
    @ColumnInfo(name = COLUMN_NAME)
    val name: String,
) {
    companion object {
        const val TABLE_NAME = "GroupTable"

        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
    }
}
