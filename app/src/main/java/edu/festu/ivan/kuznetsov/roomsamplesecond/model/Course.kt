package edu.festu.ivan.kuznetsov.roomsamplesecond.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class Course(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_course")
    var id:Long,
    @ColumnInfo(name = "name")
    var name: String)
