package edu.festu.ivan.kuznetsov.roomsamplesecond

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.festu.ivan.kuznetsov.roomsamplesecond.adapter.MyRecyclerAdapter
import edu.festu.ivan.kuznetsov.roomsamplesecond.databinding.ActivityMainBinding
import edu.festu.ivan.kuznetsov.roomsamplesecond.util.StudentDiffUtil
import edu.festu.ivan.kuznetsov.roomsamplesecond.viewmodel.StudentViewModel
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {
    companion object{
        private val TAG = MainActivity::class.java.simpleName
    }
    private lateinit var binding:ActivityMainBinding
    private lateinit var adapter: MyRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model = ViewModelProvider(this)[StudentViewModel::class.java]
        model.context = WeakReference(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        adapter = MyRecyclerAdapter()


        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.layoutPosition
                model.remove(position)
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)


        model.getStudents().observe(this) {
            Log.d(TAG, "OBSERVE")
            val productDiffUtilCallback =
                StudentDiffUtil(adapter.getStudents(), it)
            val productDiffResult =
                DiffUtil.calculateDiff(productDiffUtilCallback)
            adapter.setStudents(it)
            productDiffResult.dispatchUpdatesTo(adapter)
        }

        model.getStudentsFromDatabase()

        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

    }
}