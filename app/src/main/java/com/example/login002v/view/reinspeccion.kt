package com.example.login002v.view


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Factory
import androidx.compose.material.icons.outlined.Scale
import androidx.compose.material.icons.outlined.Tag
import androidx.compose.material.icons.outlined.Timelapse
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.login002v.viewmodel.ReinspeccionViewModel


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReinspeccionScreen(  navController: NavController, vm: ReinspeccionViewModel) {
    val s = vm.state.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Reinspección de cilindros",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "Completa los datos para continuar",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                actions = {
                    AssistChip(
                        onClick = { /* opcional */ },
                        label = { Text("Año ${s.anioProceso}") }
                    )
                    Spacer(Modifier.width(12.dp))
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

                    // --- Campos ---
                    FormField(
                        value = s.dia,
                        onValueChange = vm::onDiaChanged,
                        label = "Día",
                        placeholder = "1–31",
                        leadingIcon = { Icon(Icons.Outlined.CalendarMonth, contentDescription = null) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        supportingText = "Ej: 5"
                    )

                    Spacer(Modifier.height(12.dp))

                    FormField(
                        value = s.empresa,
                        onValueChange = vm::onEmpresaChanged,
                        label = "Empresa",
                        placeholder = "Nombre de la empresa",
                        leadingIcon = { Icon(Icons.Outlined.Factory, contentDescription = null) },
                        supportingText = "Ej: GasAndes"
                    )

                    Spacer(Modifier.height(12.dp))

                    FormField(
                        value = s.capacidadKg,
                        onValueChange = vm::onCapacidadChanged,
                        label = "Capacidad (kg)",
                        placeholder = "Ej: 15.0",
                        leadingIcon = { Icon(Icons.Outlined.Scale, contentDescription = null) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        supportingText = "Usa punto para decimales"
                    )

                    Spacer(Modifier.height(12.dp))

                    FormField(
                        value = s.ultimaSerie,
                        onValueChange = vm::onUltimaSerieChanged,
                        label = "Última serie",
                        placeholder = "Código/serie",
                        leadingIcon = { Icon(Icons.Outlined.Tag, contentDescription = null) },
                        supportingText = "Ej: A-1029"
                    )

                    Spacer(Modifier.height(12.dp))

                    FormField(
                        value = s.turno,
                        onValueChange = vm::onTurnoChanged,
                        label = "Turno",
                        placeholder = "Ej: 2",
                        leadingIcon = { Icon(Icons.Outlined.Timelapse, contentDescription = null) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        supportingText = "Ingresa el número de turno"
                    )

                    Spacer(Modifier.height(18.dp))

                    // --- CTA ---
                    Button(
                        onClick = {
                            // (Opcional) si quieres mantener validaciones, deja vm::onContinuar y navega al pasar.
                            // Por ahora: navegar directo
                            navController.navigate("marcacionesdiarias")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Continuar", style = MaterialTheme.typography.titleMedium)
                    }
                    // fin Button
                }
            }

            Spacer(Modifier.height(14.dp))

            // Pie sutil (opcional)
            Text(
                text = "Tip: revisa que el día y el turno estén correctos antes de continuar.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}

@Composable
private fun FormField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    supportingText: String? = null,
    isError: Boolean = false,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        leadingIcon = leadingIcon,
        keyboardOptions = keyboardOptions,
        singleLine = true,
        isError = isError,
        supportingText = if (supportingText != null) {
            { Text(supportingText) }
        } else null,
        shape = RoundedCornerShape(14.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary
        )
    )
}
