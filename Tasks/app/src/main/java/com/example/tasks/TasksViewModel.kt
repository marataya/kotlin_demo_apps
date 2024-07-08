package com.example.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TasksViewModel(val dao: TaskDao) : ViewModel() {
    private val _navigateToTask = MutableLiveData<Long?>()
    val navigateToTask: LiveData<Long?>
        get() = _navigateToTask
    var newTaskName = ""
    val tasks = dao.getAll()
//    var tasksString = tasks.asFlow().map {taskList ->
//        taskList.fold("") { str, item -> str + '\n' + formatTask(item) }
//    }.asLiveData()

//    fun formatTask(task: Task): String {
//        var str = "ID: ${task.taskId}"
//        str += '\n' + "Name: ${task.taskName}"
//        str += '\n' + "Complete: ${task.taskDone}" + '\n'
//        return str
//    }

    fun addTask() {
        viewModelScope.launch {
            val task = Task()
            task.taskName = newTaskName
            dao.insert(task)
        }
    }

    fun onTaskClicked(taskId: Long) {
        _navigateToTask.value = taskId
    }

    fun onTaskNavigated() {
        _navigateToTask.value = null
    }
}