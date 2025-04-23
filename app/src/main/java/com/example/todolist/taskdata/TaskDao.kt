package com.example.todolist.taskdata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskEntity)

    @Query("SELECT * FROM tasks ORDER BY date, time")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE date >= :today ORDER BY date, time")
    fun getUpcomingTasks(today: String): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE priority = :priority ORDER BY date, time")
    fun filterByPriority(priority: String): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE date = :date ORDER BY time")
    fun filterByDate(date: String): Flow<List<TaskEntity>>

    @Query("UPDATE tasks SET status = :status WHERE id = :id")
    suspend fun updateTaskStatus(id: Int, status: String)

}
