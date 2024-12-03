package com.example.exameneventosapp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewScheduleScreen(navController: NavController, schedule: Map<String, Map<String, Pair<String, String>>>) {
    var selectedDay by remember { mutableStateOf("") }
    var expandedDay by remember { mutableStateOf(false) }
    val days = listOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ver Horario") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("main") }) {
                        Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Button(onClick = { expandedDay = true }) {
                    Text(text = if (selectedDay.isEmpty()) "Selecciona el día" else selectedDay)
                }
                DropdownMenu(
                    expanded = expandedDay,
                    onDismissRequest = { expandedDay = false }
                ) {
                    days.forEach { day ->
                        DropdownMenuItem(
                            text = { Text(day) },
                            onClick = {
                                selectedDay = day
                                expandedDay = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (selectedDay.isNotEmpty()) {
                val daySchedule = schedule[selectedDay] ?: emptyMap()
                val sortedSchedule = daySchedule.toSortedMap()

                Column {
                    sortedSchedule.forEach { (startTime, endTimeSubjectPair) ->
                        val (endTime, subject) = endTimeSubjectPair
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "$startTime - $endTime", style = MaterialTheme.typography.bodyLarge)
                                Text(text = subject, style = MaterialTheme.typography.bodyLarge)
                            }
                        }
                    }
                }
            }
        }
    }
}