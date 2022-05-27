package edu.festu.ivan.kuznetsov.roomsamplesecond.util

import androidx.recyclerview.widget.DiffUtil
import edu.festu.ivan.kuznetsov.roomsamplesecond.model.Student

/**
 * Класс для быстрого обновления содержимого RecyclerView.
 * Сверяет содержимое двух список (старого и нового) и заменяет нужные позиции в RV.
 * Используется алгоритм Майерса.
 * */
class StudentDiffUtil(private val oldList: List<Student>, private val newList: List<Student>):DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    //Проверяет, тот же ли элемент остался в RV. Если да, то вызывается areContentsTheSame, иначе уже можно менять элемент списка
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
    //Проверяет, изменилось ли что-то в элементе, если да, то можно менять элемент.
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}