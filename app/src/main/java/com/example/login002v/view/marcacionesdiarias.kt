package com.example.login002v.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarcacionesDiariasScreen(navController: NavController) {

    // Estado local (puedes migrarlo a ViewModel después)
    var compania by remember { mutableStateOf("") }
    var fabricante by remember { mutableStateOf("") }
    var anioFabricacion by remember { mutableStateOf("") }
    var capacidadKg by remember { mutableStateOf("") }
    var numeroSerie by remember { mutableStateOf("") }
    var taraIngreso by remember { mutableStateOf("") }
    var taraSalida by remember { mutableStateOf("") }
    var repara1 by remember { mutableStateOf(false) }
    var repara2 by remember { mutableStateOf(false) }
    var perforaciones by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Marcaciones diarias", style = MaterialTheme.typography.titleLarge)
                        Text(
                            "Ingrese datos del cilindro",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(Modifier.height(12.dp))

            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(Modifier.padding(16.dp)) {

                    FormField(
                        value = compania,
                        onValueChange = { compania = it },
                        label = "Compañía",
                        placeholder = "Ej: LIPIGAS"
                    )

                    Spacer(Modifier.height(12.dp))

                    FormField(
                        value = fabricante,
                        onValueChange = { fabricante = it },
                        label = "Fabricante",
                        placeholder = "Ej: INDURA"
                    )

                    Spacer(Modifier.height(12.dp))

                    FormField(
                        value = anioFabricacion,
                        onValueChange = { anioFabricacion = it },
                        label = "Año fabricación",
                        placeholder = "Ej: 2020",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    Spacer(Modifier.height(12.dp))

                    FormField(
                        value = capacidadKg,
                        onValueChange = { capacidadKg = it },
                        label = "Capacidad (kg)",
                        placeholder = "Ej: 15",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    Spacer(Modifier.height(12.dp))

                    FormField(
                        value = numeroSerie,
                        onValueChange = { numeroSerie = it },
                        label = "N° serie",
                        placeholder = "Ej: A-1029"
                    )

                    Spacer(Modifier.height(12.dp))

                    FormField(
                        value = taraIngreso,
                        onValueChange = { taraIngreso = it },
                        label = "Tara ingreso",
                        placeholder = "Ej: 12.5",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                    )

                    Spacer(Modifier.height(12.dp))

                    FormField(
                        value = taraSalida,
                        onValueChange = { taraSalida = it },
                        label = "Tara salida",
                        placeholder = "Ej: 12.1",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                    )

                    Spacer(Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(18.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = repara1, onCheckedChange = { repara1 = it })
                            Spacer(Modifier.width(6.dp))
                            Text("Repara 1")
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = repara2, onCheckedChange = { repara2 = it })
                            Spacer(Modifier.width(6.dp))
                            Text("Repara 2")
                        }
                    }

                    Spacer(Modifier.height(12.dp))

                    FormField(
                        value = perforaciones,
                        onValueChange = { perforaciones = it },
                        label = "Perforaciones",
                        placeholder = "Ej: 0 / detalle",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )

                    Spacer(Modifier.height(18.dp))

                    Button(
                        onClick = {
                            // acá puedes validar/guardar y navegar
                            // navController.navigate("...") si corresponde
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Guardar", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}

@Composable
private fun FormField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isError: Boolean = false,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        keyboardOptions = keyboardOptions,
        singleLine = true,
        isError = isError,
        shape = RoundedCornerShape(14.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary
        )
    )
}
