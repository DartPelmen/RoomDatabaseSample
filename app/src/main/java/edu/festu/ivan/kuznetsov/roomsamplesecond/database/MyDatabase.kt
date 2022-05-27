package edu.festu.ivan.kuznetsov.roomsamplesecond.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import edu.festu.ivan.kuznetsov.roomsamplesecond.dao.StudentDao
import edu.festu.ivan.kuznetsov.roomsamplesecond.model.Student
import java.util.concurrent.Executors


@Database(entities = [Student::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun getStudentDao(): StudentDao
    //Double-checked singleton
    companion object {
        @Volatile
        private lateinit var instance: MyDatabase


        fun getInstance(context: Context): MyDatabase {
            if (! ::instance.isInitialized)
                synchronized(MyDatabase::class.java) {
                    if (! ::instance.isInitialized) {
                        //Применяется паттерн Builder
                        instance = Room
                            .databaseBuilder(context,MyDatabase::class.java, "my_database")
                            .addCallback(object : RoomDatabase.Callback(){
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    val student = Student("ivan","ivanov")
                                    val student2 = Student("petr","petrov")
                                    Executors.newSingleThreadExecutor().execute { instance.getStudentDao().insertStudent(student,student2) }
                                }
                            } )
                            .build()
                    }
                }
            return instance
        }
    }
}