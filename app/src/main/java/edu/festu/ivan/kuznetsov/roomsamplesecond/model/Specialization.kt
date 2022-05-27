package edu.festu.ivan.kuznetsov.roomsamplesecond.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "specializations")
data class Specialization(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_specialization")
    var id:Long,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "description")
    var description: String)

