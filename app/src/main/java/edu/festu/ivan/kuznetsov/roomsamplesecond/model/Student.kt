package edu.festu.ivan.kuznetsov.roomsamplesecond.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
   @ColumnInfo(name = "first_name")
    var firstName: String,
    @ColumnInfo(name = "last_name")
    var lastName: String) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_student")
    var id:Long = 0

    override fun toString(): String {
        return "Student(id=$id, firstName='$firstName', lastName='$lastName')"
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Student)
            other.firstName ==this.firstName && other.lastName == this.lastName
        else
            false
    }
}
