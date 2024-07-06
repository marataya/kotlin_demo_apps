package com.example.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

class TaskItemAdapter : RecyclerView.Adapter<TaskItemAdapter.TaskItemViewHolder>() {
    var data = listOf<Task>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder = TaskItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)

    }

    override fun getItemCount(): Int = data.size

    class TaskItemViewHolder(val rootView: TextView) : RecyclerView.ViewHolder(rootView) {
        fun bind(item: Task) {
            rootView.text = item.taskName
        }

        companion object {
            fun inflateFrom(parent: ViewGroup) : TaskItemViewHolder {
                val layoutInflator = LayoutInflater.from(parent.context)
                val view = layoutInflator.inflate(R.layout.task_item, parent, false) as TextView
                return TaskItemViewHolder(view)
            }
        }
    }
}

