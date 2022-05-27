package edu.festu.ivan.kuznetsov.roomsamplesecond.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.festu.ivan.kuznetsov.roomsamplesecond.databinding.ItemBinding
import edu.festu.ivan.kuznetsov.roomsamplesecond.model.Student


//Реализация паттерна adapter
class MyRecyclerAdapter: RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>() {
    //То, что должно показаться на RV
    private var students: MutableList<Student> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    fun setStudents(students: MutableList<Student>){
        this.students = students
    }
    fun getStudents(): MutableList<Student>{
        return students
    }
    //Как показывать содержимое ячейки
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = students[position]
        holder.binding.idField.text = item.id.toString()
        holder.binding.firstNameField.text = item.firstName
        holder.binding.lastNameField.text = item.lastName

    }

    override fun getItemCount(): Int = students.size
    //отвечает за ячейку
    inner class ViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root){


        override fun toString(): String {
            itemView
            return super.toString() + " '" + binding.firstNameField.text + "'"
        }
    }
}