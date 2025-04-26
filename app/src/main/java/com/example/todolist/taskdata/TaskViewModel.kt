package com.example.todolist.taskdata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = DatabaseProvider.getDb(application).taskDao()

    val allTasks: Flow<List<TaskEntity>> = dao.getAllTasks()

    fun getUpcomingTasks(): Flow<List<TaskEntity>> {
        val today = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        return dao.getUpcomingTasks(today)
    }

    fun filterByPriority(priority: String): Flow<List<TaskEntity>> {
        return dao.filterByPriority(priority)
    }

    fun filterByDate(date: String): Flow<List<TaskEntity>> {
        return dao.filterByDate(date)
    }
    fun updateTaskStatus(id: Int, status: String) {
        viewModelScope.launch {
            dao.updateTaskStatus(id, status)
        }
    }

    fun deleteTask(taskEntity: TaskEntity)
    {
        viewModelScope.launch {
            dao.delete(taskEntity)
        }
    }

}
