

package com.example.madhumarga.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.madhumarga.viewmodel.HiveViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsScreen(viewModel: HiveViewModel) {

    val hives by viewModel.hives.collectAsState()

    val totalHoney = hives.sumOf { it.honeyCollected }

    val averageHoney =
        if (hives.isNotEmpty()) totalHoney / hives.size
        else 0

    val bestHive =
        hives.maxByOrNull { it.honeyCollected }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Harvest Analytics") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            AnalyticsCard(
                title = "Total Hives",
                value = "${hives.size}"
            )

            AnalyticsCard(
                title = "Total Honey Collected",
                value = "$totalHoney kg"
            )

            AnalyticsCard(
                title = "Average Honey Production",
                value = "$averageHoney kg"
            )

            AnalyticsCard(
                title = "Best Performing Hive",
                value = bestHive?.location ?: "No Data"
            )
        }
    }
}

@Composable
fun AnalyticsCard(title: String, value: String) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}