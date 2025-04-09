package com.example.todolist

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Calendar

class CreateTaskActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateTaskScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateTaskScreenP() {
    CreateTaskScreen()
}

@Composable
fun CreateTaskScreen() {

    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var eventCategory by remember { mutableStateOf("") }

    var dateState by remember { mutableStateOf("") }
    var timeState by remember { mutableStateOf("") }

    val context = LocalContext.current as Activity

    val calendar = Calendar.getInstance()

    var selYear by remember { mutableIntStateOf(0) }
    var selMonth by remember { mutableIntStateOf(0) }
    var selDOM by remember { mutableIntStateOf(0) }
    var selHOD by remember { mutableIntStateOf(0) }
    var selMinute by remember { mutableIntStateOf(0) }

    var isChecked by remember { mutableStateOf(false) }

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            selYear = year
            selMonth = month
            selDOM = dayOfMonth


            Log.e("Test", "Y - $year , M - ${month + 1} , DOM - $dayOfMonth")

            dateState = String.format("%02d/%02d/%d", dayOfMonth, month + 1, year)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    // Open Time Picker
    val timePickerDialog = TimePickerDialog(
        context,
        { _, hourOfDay, minute ->

            selHOD = hourOfDay
            selMinute = minute



            Log.e("Test", "HOD - $hourOfDay , Minute - $minute")

            timeState = String.format("%02d:%02d", hourOfDay, minute)
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        false
    )


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
                    .size(36.dp),
                painter = painterResource(id = R.drawable.baseline_arrow_back_36),
                contentDescription = "Task"
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Create Task",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {


            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = taskName,
                onValueChange = { taskName = it }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                modifier = Modifier,
                text = "Task Description",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.Red,
                )
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = taskDescription,
                minLines = 3,
                onValueChange = { taskDescription = it }
            )

            Spacer(modifier = Modifier.height(12.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp)
                        .height(50.dp)
                        .clickable {
                            // Handle the click event, e.g., show a date picker
                        }
                        .background(Color.LightGray, MaterialTheme.shapes.medium)
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = dateState.ifEmpty { "Event Date" },
                        color = if (dateState.isEmpty()) Color.Gray else Color.Black
                    )
                    Icon(
                        imageVector = Icons.Default.DateRange, // Replace with your desired icon
                        contentDescription = "Calendar Icon",
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .size(24.dp)
                            .clickable {
                                datePickerDialog.show()
                            },
                        tint = Color.DarkGray
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp)
                        .height(50.dp)
                        .clickable {
                            // Handle the click event, e.g., show a date picker
                        }
                        .background(Color.LightGray, MaterialTheme.shapes.medium)
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = timeState.ifEmpty { "Event Time" },
                        color = if (timeState.isEmpty()) Color.Gray else Color.Black
                    )
                    Icon(
                        imageVector = Icons.Default.DateRange, // Replace with your desired icon
                        contentDescription = "Calendar Icon",
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .size(24.dp)
                            .clickable {
                                timePickerDialog.show()
                            },
                        tint = Color.DarkGray
                    )
                }

            }

            Spacer(modifier = Modifier.height(8.dp))

            PriorityLevelChips()

            Spacer(modifier = Modifier.height(8.dp))

            RepeatStatusChips()

            Spacer(modifier = Modifier.height(8.dp))


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                )
                Text(
                    text = "Notify me about this task",
                    modifier = Modifier.padding(start = 8.dp)
                )

            }


            Spacer(modifier = Modifier.height(24.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                    }
                    .background(
                        color = colorResource(id = R.color.soft_peach),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(vertical = 6.dp),
                text = "Save Task",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.White,
                )
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PriorityLevelChips() {
    val levels = listOf("High", "Medium", "Low")
    val selectedLevels = remember { mutableStateListOf<String>() }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Priority Level",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            levels.forEach { mood ->
                FilterChip(
                    selected = mood in selectedLevels,
                    onClick = {
                        if (mood in selectedLevels) {
                            selectedLevels.remove(mood)
                        } else {
                            selectedLevels.add(mood)
                        }
                    },
                    label = { Text(mood) }
                )
            }
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RepeatStatusChips() {
    val levels = listOf("One-Time", "Daily", "Weekly", "Monthly")
    val repeatLevels = remember { mutableStateListOf<String>() }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Priority Level",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            levels.forEach { mood ->
                FilterChip(
                    selected = mood in repeatLevels,
                    onClick = {
                        if (mood in repeatLevels) {
                            repeatLevels.remove(mood)
                        } else {
                            repeatLevels.add(mood)
                        }
                    },
                    label = { Text(mood) }
                )
            }
        }
    }
}
