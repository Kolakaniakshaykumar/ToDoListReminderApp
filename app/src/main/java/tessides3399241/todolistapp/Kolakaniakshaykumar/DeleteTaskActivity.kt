package tessides3399241.todolistapp.Kolakaniakshaykumar

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.sp
import tessides3399241.todolistapp.Kolakaniakshaykumar.taskdata.TaskEntity
import tessides3399241.todolistapp.Kolakaniakshaykumar.taskdata.TaskViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DeleteTaskActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeleteTaskListScreen(taskViewModel = TaskViewModel(application))
        }
    }
}

@Composable
fun DeleteTaskListScreen(taskViewModel: TaskViewModel) {
    val context = LocalContext.current
    val taskList by taskViewModel.allTasks.collectAsState(initial = emptyList())

    var selectedTask by remember { mutableStateOf<TaskEntity?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var selectedStatus by remember { mutableStateOf("Pending") }

    Column(
        modifier = Modifier.fillMaxSize().padding(WindowInsets.systemBars.asPaddingValues())
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
                    .clickable { (context as Activity).finish() },
                painter = painterResource(id = R.drawable.baseline_arrow_back_36),
                contentDescription = "Back"
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Delete Tasks",
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


            if (taskList.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(taskList) { task ->
                        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        val isExpired = try {
                            val taskDate = sdf.parse(task.date)
                            taskDate?.before(Date()) == true && task.status == "Pending"
                        } catch (e: Exception) {
                            false
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(task.name, fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.weight(1f))
                                    IconButton(onClick = {
                                        taskViewModel.deleteTask(task)
                                        Toast.makeText(
                                            context,
                                            "Task Deleted Successfully",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                    }) {
                                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                                    }
                                }

                                Text(task.description)

                                Text("${task.date} ${task.time}")
                                Text("Priority: ${task.priority} | Status: ${task.status}")

                            }
                        }
                    }
                }
            } else {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    text = "No Tasks Found",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFFFFA000)
                )
            }
        }
    }

    if (showDialog && selectedTask != null) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Update Task Status") },
            text = {
                Column {
                    Text("Task: ${selectedTask!!.name}")
                    Text("Date: ${selectedTask!!.date}")
                    Text("Current Status: ${selectedTask!!.status}")
                    Spacer(Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedStatus == "Completed",
                            onClick = { selectedStatus = "Completed" })
                        Text("Completed")
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedStatus == "Missed",
                            onClick = { selectedStatus = "Missed" })
                        Text("Missed")
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    selectedTask?.let {
                        taskViewModel.updateTaskStatus(it.id, selectedStatus)
                        showDialog = false
                        Toast.makeText(context, "Status updated", Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}