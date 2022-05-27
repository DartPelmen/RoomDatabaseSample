package edu.festu.ivan.kuznetsov.roomsamplesecond.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.festu.ivan.kuznetsov.roomsamplesecond.database.MyDatabase
import edu.festu.ivan.kuznetsov.roomsamplesecond.model.Student
import java.lang.ref.WeakReference
import java.util.concurrent.Executors
/**
 * ViewModel. Нужна для того, чтобы связывать Модель и Представление.
 * В виде представления у нас RecyclerView с его @Adapter.
 * Моделью будет MyDatabase и её функционал.
 * */
class StudentViewModel : ViewModel() {
    lateinit var context: WeakReference<Context>
    private val students = MutableLiveData<MutableList<Student>>() //Изменяемые данные. VM будет уведомлять об их изменении.
    //Executor для БД
    init {
        students.value = mutableListOf()
    }
    private val executor = Executors.newSingleThreadExecutor()


    fun getStudents():MutableLiveData<MutableList<Student>>{
        return students
    }
    //Метод для добавления Student в БД
    fun addStudents(student: Student){
        context.get()?.let {
            executor.submit {
                MyDatabase.getInstance(it).getStudentDao().insertStudent(student)
                students.value?.add(student)
                students.postValue(students.value)
            }
        }
    }

    fun getStudentsFromDatabase() {
        context.get()?.let {
            executor.submit {
                students.value?.addAll(MyDatabase.getInstance(it).getStudentDao().getAll())
                students.postValue(students.value)
            }
        }
    }

    fun remove(position: Int) {
        context.get()?.let {ctx->
            Executors.newSingleThreadExecutor().execute{
                students.value?.let {
                    MyDatabase.getInstance(ctx).getStudentDao().deleteStudent(it[position])
                    students.value?.removeAt(position)
                    students.postValue(students.value)
                }
            }
        }
    }
}