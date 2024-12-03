package com.example.exameneventosapp

import CurrentSubjectHelper
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ExameneventosappTheme {
                val navController = rememberNavController()
                val schedule = remember { mutableStateOf(mapOf<String, Map<String, Pair<String, String>>>()) }
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("") },
                            navigationIcon = {
                                if (currentRoute != "main") {
                                    IconButton(onClick = { navController.navigateUp() }) {
                                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                                    }
                                } else {
                                    IconButton(onClick = { navController.navigate("main") }) {
                                        Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
                                    }
                                }
                            }
                        )
                    }
                ) {
                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") { MainScreen(navController, schedule.value) }
                        composable("addSchedule") { AddScheduleScreen(navController, schedule) }
                        composable("viewSchedule") { ViewScheduleScreen(navController, schedule.value) }
                        composable("currentSubject/{subject}") { backStackEntry ->
                            val subject = backStackEntry.arguments?.getString("subject")
                            CurrentSubjectScreen(navController, subject)
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScreen(navController: NavController, schedule: Map<String, Map<String, Pair<String, String>>>) {
        val context = LocalContext.current
        val currentSubjectHelper = remember { CurrentSubjectHelper(context, navController) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { navController.navigate("addSchedule") }) {
                Text(text = "Agregar Asignatura", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("viewSchedule") }) {
                Text(text = "Ver Horario", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { currentSubjectHelper.showCurrentSubject(schedule) }) {
                Text(text = "Asignatura Actual", fontSize = 18.sp)
            }
        }
    }
}