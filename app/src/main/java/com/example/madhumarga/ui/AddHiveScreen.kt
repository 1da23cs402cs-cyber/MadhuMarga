package com.example.madhumarga.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.madhumarga.data.Hive
import com.example.madhumarga.data.HiveLog
import com.example.madhumarga.viewmodel.HiveViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddHiveScreen(viewModel: HiveViewModel, onBack: () -> Unit) {

    var location by remember { mutableStateOf("") }
    var honey by remember { mutableStateOf("") }

    var queenPresent by remember { mutableStateOf(true) }


    var pestsSeen by remember { mutableStateOf(false) }

    var activityLevel by remember { mutableStateOf("Normal") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add New Hive") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Location") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = honey,
                onValueChange = { honey = it },
                label = { Text("Honey Collected (kg)") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth()
            )

            // Queen Present
            Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = queenPresent,
                    onCheckedChange = { queenPresent = it }
                )

                Text("Queen Present")
            }

            // NEW — Pests Seen
            Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = pestsSeen,
                    onCheckedChange = { pestsSeen = it }
                )

                Text("Any Pests Seen?")
            }

            // Activity Level
            Text(
                "Activity Level",
                style = MaterialTheme.typography.labelLarge
            )

            val activities = listOf("Low", "Normal", "High")

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                activities.forEach { level ->

                    FilterChip(
                        selected = activityLevel == level,
                        onClick = { activityLevel = level },
                        label = { Text(level) }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {

                    if (location.isNotBlank()) {

                        val newHive = Hive(
                            location = location,
                            queenPresent = queenPresent,

                            // NEW
                            pestsSeen = pestsSeen,

                            activityLevel = activityLevel,
                            honeyCollected = honey.toIntOrNull() ?: 0
                        )

                        viewModel.addHive(newHive)

                        viewModel.addLog(
                            HiveLog(
                                hiveId = 0,
                                inspectionNotes = "Initial setup at $location",

                                healthStatus =
                                    if (activityLevel == "Low" || pestsSeen)
                                        "Immediate inspection required"
                                    else
                                        "Healthy"
                            )
                        )

                        onBack()
                    }
                },

                modifier = Modifier.fillMaxWidth(),

                enabled = location.isNotBlank()

            ) {

                Text("Save Hive")
            }
        }
    }
}