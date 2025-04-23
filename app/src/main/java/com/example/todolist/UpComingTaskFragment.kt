package com.example.todolist

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.taskdata.TaskEntity
import com.example.todolist.taskdata.TaskViewModel
import com.example.todolist.taskdata.TaskViewModelFactory
import com.example.todolist.ui.theme.TodoListTheme

class UpComingTaskFragment : Fragment(R.layout.fragment_up_coming_task) {

    private lateinit var taskViewModel: TaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireActivity().application

        taskViewModel = ViewModelProvider(
            this,
            TaskViewModelFactory(application)
        )[TaskViewModel::class.java]

        view.findViewById<ComposeView>(R.id.upcomingTasks).setContent {
            TodoListTheme {
                UpcomingTasksScreen(viewModel = taskViewModel)
            }
        }
    }
}


data class Task(
    val name: String,
    val description: String,
    val date: String,
    val time: String,
    val priority: String
)

@Composable
fun UpcomingTasksScreen(viewModel: TaskViewModel) {

    val upcomingTasks by viewModel.getUpcomingTasks().collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.soft_peach))
                .padding(horizontal = 12.dp, vertical = 4.dp),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Upcoming Tasks",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {

            if (upcomingTasks.isNotEmpty()) {

                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(upcomingTasks.size) { task ->
                        TaskCard(upcomingTasks[task])
                    }
                }

            } else {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "No Upcoming Tasks",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Composable
fun TaskCard(task: TaskEntity) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = task.name, fontWeight = FontWeight.Bold)
                Text(
                    text = task.priority,
                    color = when (task.priority) {
                        "High" -> Color.Red
                        "Medium" -> Color(0xFFFFA500)
                        else -> Color.Gray
                    },
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = task.description, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Icon(
                    Icons.Default.DateRange,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Text(text = " ${task.date}", style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.width(12.dp))
                Icon(
                    Icons.Default.DateRange,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Text(text = " ${task.time}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
