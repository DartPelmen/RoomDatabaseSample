package edu.festu.ivan.kuznetsov.roomsamplesecond.dao

import androidx.room.*
import edu.festu.ivan.kuznetsov.roomsamplesecond.model.Student

/**
 * Database Access Object.
 * Организует способ работы с частями БД - в нашем случае с таблицей Students.
 * Реализацию интерфейса Room сделает сама.
 * */
@Dao
interface StudentDao {
    //c++: virtual Student insertStudent(Student student) = 0;
    @Insert
    fun insertStudent(vararg student: Student)
    @Update
    fun updateStudent(student: Student)
    @Delete
    fun deleteStudent(student: Student)
    @Query("SELECT * FROM STUDENTS")
    fun getAll():MutableList<Student>
    @Query("SELECT * FROM STUDENTS WHERE first_name = :firstName")
    fun getAllByFirstName(firstName: String): MutableList<Student>
}