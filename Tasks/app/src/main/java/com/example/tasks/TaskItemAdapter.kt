package com.example.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TaskItemAdapter : ListAdapter<Task, TaskItemAdapter.TaskItemViewHolder>(TaskDiffItemCallback()) {
//    var data = listOf<Task>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder = TaskItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
//        val item = data[position]
        val item = getItem(position)
        holder.bind(item)
    }

//    override fun getItemCount(): Int = data.size

    class TaskItemViewHolder(val rootView: CardView) : RecyclerView.ViewHolder(rootView) {
        val taskNameView = rootView.findViewById<TextView>(R.id.task_name)
        val taskDoneView = rootView.findViewById<CheckBox>(R.id.task_done)

        fun bind(item: Task) {
            taskNameView.text = item.taskName
            taskDoneView.isChecked = item.taskDone
        }

        companion object {
            fun inflateFrom(parent: ViewGroup) : TaskItemViewHolder {
                val layoutInflator = LayoutInflater.from(parent.context)
                val view = layoutInflator.inflate(R.layout.task_item, parent, false) as CardView
                return TaskItemViewHolder(view)
            }
        }
    }
}