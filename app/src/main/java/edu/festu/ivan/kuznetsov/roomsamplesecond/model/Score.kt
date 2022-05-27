package edu.festu.ivan.kuznetsov.roomsamplesecond.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "scores")
data class Score(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_score")
    var id:Long,
    @ColumnInfo(name = "value")
    var value: String,
    @ColumnInfo(name = "date")
    var date: Date)
