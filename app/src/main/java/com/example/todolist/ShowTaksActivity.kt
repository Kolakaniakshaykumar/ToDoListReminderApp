package com.example.todolist

import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todolist.taskdata.TaskViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ShowTaksActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { 
            TaskListScreen(viewModel = TaskViewModel(application))
        }
    }
}

@Composable
fun TaskListScreen(viewModel: TaskViewModel) {
    val allTasks by viewModel.allTasks.collectAsState(initial = emptyList())

    var selectedPriority by remember { mutableStateOf("All") }
    var selectedDate by remember { mutableStateOf("") }

    val context = LocalContext.current as Activity

    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, day ->
            selectedDate = String.format("%02d/%02d/%04d", day, month + 1, year)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val filteredTasks = allTasks.filter { task ->
        (selectedPriority == "All" || task.priority == selectedPriority) &&
                (selectedDate.isEmpty() || task.date == selectedDate)
    }


    val formatter = remember { SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()) }
    val currentDate = remember { Date() }

    var showStatusDialog by remember { mutableStateOf(false) }
    var selectedTaskId by remember { mutableIntStateOf(-1) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.soft_peach))
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(36.dp)
                    .clickable { context.finish() },
                painter = painterResource(id = R.drawable.baseline_arrow_back_36),
                contentDescription = "Back"
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Added Tasks",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        // Filters
        Column(modifier = Modifier.padding(12.dp)) {
            Text("Filter by Priority")

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("All", "High", "Medium", "Low").forEach { priority ->
                    FilterChip(
                        selected = selectedPriority == priority,
                        onClick = { selectedPriority = priority },
                        label = { Text(priority) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Filter by Date: ${selectedDate.ifEmpty { "None" }}")
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { datePickerDialog.show() }) {
                    Text("Select Date")
                }
                if (selectedDate.isNotEmpty()) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear date",
                        modifier = Modifier.clickable { selectedDate = "" }
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
        }

//        LazyColumn(modifier = Modifier.padding(horizontal = 12.dp)) {
//            items(filteredTasks.size) { index ->
//                val task = filteredTasks[index]
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 4.dp),
//                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F2F2))
//                ) {
//                    Column(modifier = Modifier.padding(12.dp)) {
//                        Text(task.name, fontWeight = FontWeight.Bold)
//                        Text(task.description)
//                        Text("Date: ${task.date}, Time: ${task.time}")
//                        Text("Priority: ${task.priority}")
//                    }
//                }
//            }
//        }

        LazyColumn(modifier = Modifier.padding(horizontal = 12.dp)) {
            items(filteredTasks.size) { index ->
                val task = filteredTasks[index]

                // Combine date and time for comparison
                val taskDateTime = formatter.parse("${task.date} ${task.time}")

                val isExpired = taskDateTime?.before(currentDate) == true && task.status.isEmpty()

                if (isExpired && selectedTaskId != task.id) {
                    selectedTaskId = task.id
                    showStatusDialog = true
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F2F2))
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(task.name, fontWeight = FontWeight.Bold)
                        Text(task.description)
                        Text("Date: ${task.date}, Time: ${task.time}")
                        Text("Priority: ${task.priority}")
                        Text("Status: ${task.status.ifEmpty { "Pending" }}")
                    }
                }
            }
        }

        if (showStatusDialog && selectedTaskId != -1) {
            AlertDialog(
                onDismissRequest = { showStatusDialog = false },
                title = { Text("Task Status") },
                text = { Text("Has this expired task been completed?") },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.updateTaskStatus(selectedTaskId, "Completed")
                        showStatusDialog = false
                    }) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        viewModel.updateTaskStatus(selectedTaskId, "Not Completed")
                        showStatusDialog = false
                    }) {
                        Text("No")
                    }
                }
            )
        }

    }
}


@Composable
fun TaskListScreenOld(viewModel: TaskViewModel ) {
    val tasks by viewModel.allTasks.collectAsState(initial = emptyList())

    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.soft_peach))
                .padding(horizontal = 12.dp, vertical = 4.dp),
        ) {

            Image(
                modifier = Modifier
                    .size(36.dp)
                    .clickable {
                        context.finish()
                    },
                painter = painterResource(id = R.drawable.baseline_arrow_back_36),
                contentDescription = "Task"
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Added Tasks",
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

            LazyColumn {
                items(tasks.size) { task ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F2F2))
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(tasks[task].name, fontWeight = FontWeight.Bold)
                            Text(tasks[task].description)
                            Text("Date: ${tasks[task].date}, Time: ${tasks[task].time}")
                            Text("Priority: ${tasks[task].priority}")
                        }
                    }
                }
            }
        }
    }
}
