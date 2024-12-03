package com.example.exameneventosapp

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import java.util.*

//pantalla para añadir una asignatura al horario
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScheduleScreen(navController: NavController, scheduleState: MutableState<Map<String, Map<String, Pair<String, String>>>>) {
    //variables para guardar los datos de la asignatura
    var selectedSubject by remember { mutableStateOf("") }
    var expandedSubject by remember { mutableStateOf(false) }
    //listado de asignaturas
    val subjects = listOf("Matemáticas", "Historia", "Literatura", "Biologia", "Física", "Química", "Inglés")

    //variables para guardar los datos del dia
    var selectedDay by remember { mutableStateOf("") }
    var expandedDay by remember { mutableStateOf(false) }
    //listado de dias
    val days = listOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes")

    var selectedStartTime by remember { mutableStateOf("") }
    var selectedEndTime by remember { mutableStateOf("") }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Scaffold {
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
                //boton para seleccionar la asignatura
                Button(onClick = { expandedSubject = true }) {
                    Text(text = if (selectedSubject.isEmpty()) "Selecciona asignatura" else selectedSubject)
                }
                //menu desplegable con las asignaturas
                DropdownMenu(
                    expanded = expandedSubject,
                    onDismissRequest = { expandedSubject = false }
                ) {
                    subjects.forEach { subject ->
                        DropdownMenuItem(
                            text = { Text(subject) },
                            onClick = {
                                selectedSubject = subject
                                expandedSubject = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                //boton para seleccionar el dia
                Button(onClick = { expandedDay = true }) {
                    Text(text = if (selectedDay.isEmpty()) "Selecciona el día" else selectedDay)
                }
                //menu desplegable con los dias
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

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                //boton para seleccionar la hora de inicio
                Button(onClick = {
                    val calendar = Calendar.getInstance()
                    val timePickerDialog = TimePickerDialog( //timePicker para seleccionar la
                        // hora con un reloj
                        context,
                        { _, hourOfDay, minute ->
                            selectedStartTime = String.format("%02d:%02d", hourOfDay, minute)
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                    )
                    timePickerDialog.show()
                }) {
                    Text(text = if (selectedStartTime.isEmpty()) "Selecciona hora de inicio"
                    else selectedStartTime)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            //Finaliza timePickerDialog
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                //boton para seleccionar la hora de finalización
                Button(onClick = {
                    val calendar = Calendar.getInstance()
                    val timePickerDialog = TimePickerDialog( //timepicker tambien para
                        // seleccionar la hora de finalizacion
                        context,
                        { _, hourOfDay, minute ->
                            selectedEndTime = String.format("%02d:%02d", hourOfDay, minute)
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                    )
                    timePickerDialog.show()
                }) {
                    Text(text = if (selectedEndTime.isEmpty()) "Selecciona hora de finalización" else selectedEndTime)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            //boton para guardar la asignatura
            Button(onClick = {
                coroutineScope.launch {
                    if (selectedDay.isNotEmpty() && selectedStartTime.isNotEmpty() && selectedEndTime.isNotEmpty() && selectedSubject.isNotEmpty()) {
                        val daySchedule = scheduleState.value[selectedDay]?.toMutableMap() ?: mutableMapOf()
                        if (daySchedule.containsKey(selectedStartTime)) {
                            Toast.makeText(context, "Ya existe una asignatura a esta hora", Toast.LENGTH_SHORT).show()
                        } else {
                            daySchedule[selectedStartTime] = Pair(selectedEndTime, selectedSubject)
                            scheduleState.value = scheduleState.value.toMutableMap().apply {
                                put(selectedDay, daySchedule)
                            }
                            Toast.makeText(context, "Asignatura guardada", Toast.LENGTH_SHORT).show()
                            navController.navigate("main")
                        }
                    } else {
                        Toast.makeText(context, "Por favor, selecciona todos los campos", Toast.LENGTH_SHORT).show()
                    }
                }
            }) {
                Text("Guardar")
            }
        }
    }
}