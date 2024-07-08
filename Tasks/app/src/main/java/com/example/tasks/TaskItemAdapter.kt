package com.example.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.databinding.TaskItemBinding

class TaskItemAdapter(val clickListener: (taskId: Long) -> Unit) : ListAdapter<Task, TaskItemAdapter.TaskItemViewHolder>(TaskDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder = TaskItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
//        val item = data[position]
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

//    override fun getItemCount(): Int = data.size

    class TaskItemViewHolder(val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(item: Task, clickListener: (taskId: Long) -> Unit) {
            binding.task = item
            binding.root.setOnClickListener { clickListener(item.taskId) }
        }

        companion object {
            fun inflateFrom(parent: ViewGroup) : TaskItemViewHolder {
                val layoutInflator = LayoutInflater.from(parent.context)
//                val view = layoutInflator.inflate(R.layout.task_item, parent, false) as CardView
                val binding = TaskItemBinding.inflate(layoutInflator, parent, false)
                return TaskItemViewHolder(binding)
            }
        }
    }
}