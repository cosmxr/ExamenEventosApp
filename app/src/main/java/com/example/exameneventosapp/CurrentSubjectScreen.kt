package com.example.exameneventosapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentSubjectScreen(navController: NavController, subject: String?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Asignatura Actual") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (subject != null) {
                Text(text = "Asignatura actual: $subject", style = MaterialTheme.typography.bodyLarge)
            } else {
                Text(text = "No hay asignaturas en curso en este momento.", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}