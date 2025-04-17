package com.example.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.todolist.ui.theme.TodoListTheme

class UpComingTaskFragment : Fragment(R.layout.fragment_up_coming_task) {

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ComposeView>(R.id.upcomingTasks).setContent {
            TodoListTheme {
                UpcomingTasksScreen()
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
fun UpcomingTasksScreen() {
    val tasks = listOf(
        Task("Meeting with Client", "Discuss Q2 deliverables", "2025-04-20", "10:00 AM", "High"),
        Task("Project Review", "Team progress review", "2025-04-21", "02:00 PM", "Medium"),
        Task("Design Mockups", "Prepare UI for new module", "2025-04-22", "11:30 AM", "High"),
        Task("Code Refactoring", "Optimize legacy code", "2025-04-23", "03:00 PM", "Low"),
        Task("Submit Report", "Submit financial report", "2025-04-24", "09:00 AM", "Medium"),
        Task("Team Standup", "Daily sync-up", "2025-04-25", "10:00 AM", "Low"),
        Task("Client Demo", "Demo of new features", "2025-04-26", "01:00 PM", "High"),
        Task("Update Documentation", "API Docs and Readme", "2025-04-27", "04:00 PM", "Medium"),
        Task("Backend Testing", "Database + API layer", "2025-04-28", "02:00 PM", "High"),
        Task("Plan Sprint", "Backlog grooming + sprint plan", "2025-04-29", "11:00 AM", "Medium")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Upcoming Tasks",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(tasks.size) { task ->
                TaskCard(tasks[task])
            }
        }
    }
}

@Composable
fun TaskCard(task: Task) {
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
                Icon(Icons.Default.DateRange, contentDescription = null, modifier = Modifier.size(16.dp))
                Text(text = " ${task.date}", style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.width(12.dp))
                Icon(Icons.Default.DateRange, contentDescription = null, modifier = Modifier.size(16.dp))
                Text(text = " ${task.time}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
