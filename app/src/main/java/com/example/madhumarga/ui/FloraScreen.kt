
package com.example.madhumarga.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class FloraInfo(
    val season: String,
    val flower: String,
    val benefit: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloraScreen() {

    val floraList = listOf(
        FloraInfo("January - February", "Mustard Flowers", "Good nectar flow"),
        FloraInfo("March - April", "Sunflower", "High honey production"),
        FloraInfo("May - June", "Neem Blossoms", "Supports bee health"),
        FloraInfo("July - August", "Wild Flowers", "Natural pollen source"),
        FloraInfo("September - October", "Eucalyptus", "Strong honey flow"),
        FloraInfo("November - December", "Sesame Flowers", "Improves colony activity")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Flora Calendar") }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(floraList) { flora ->

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = flora.season,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Flower: ${flora.flower}"
                        )

                        Text(
                            text = "Benefit: ${flora.benefit}"
                        )
                    }
                }
            }
        }
    }
}