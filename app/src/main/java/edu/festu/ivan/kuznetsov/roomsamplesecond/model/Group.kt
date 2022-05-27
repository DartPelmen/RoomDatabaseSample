package edu.festu.ivan.kuznetsov.roomsamplesecond.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groups")
data class Group(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_group")
    var id:Long,
    @ColumnInfo(name = "year")
    var year: Int)
