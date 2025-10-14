package com.example.login002v.viewmodel


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class ReinspeccionViewModel : ViewModel() {

    private val _state = MutableStateFlow(ReinspeccionUiState())
    val state: StateFlow<ReinspeccionUiState> = _state.asStateFlow()

    private val _events = Channel<UiEvent>(capacity = Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun onDiaChanged(v: String) = update { it.copy(dia = v) }
    fun onEmpresaChanged(v: String) = update { it.copy(empresa = v) }
    fun onCapacidadChanged(v: String) = update { it.copy(capacidadKg = v) }
    fun onUltimaSerieChanged(v: String) = update { it.copy(ultimaSerie = v) }
    fun onTurnoChanged(v: String) = update { it.copy(turno = v) }

    fun onContinuar() {
        val s = _state.value

        val dia = s.dia.trim().toIntOrNull()
        if (dia == null || dia !in 1..31) {
            send(UiEvent.ShowToast("Día inválido (1–31)."))
            return
        }

        val empresa = s.empresa.trim()
        if (empresa.isEmpty()) {
            send(UiEvent.ShowToast("Ingrese Empresa."))
            return
        }

        val capacidad = s.capacidadKg.trim().replace(",", ".").toDoubleOrNull()
        if (capacidad == null || capacidad <= 0.0) {
            send(UiEvent.ShowToast("Capacidad inválida (> 0)."))
            return
        }

        val ultimaSerie = s.ultimaSerie.trim()
        if (ultimaSerie.isEmpty()) {
            send(UiEvent.ShowToast("Ingrese Última Serie."))
            return
        }

        val turno = s.turno.trim().toIntOrNull()
        if (turno == null || turno <= 0) {
            send(UiEvent.ShowToast("Turno inválido (> 0)."))
            return
        }

        send(
            UiEvent.NavigateNext(
                ReinspeccionPayload(
                    dia = dia,
                    empresa = empresa,
                    capacidadKg = capacidad,
                    ultimaSerie = ultimaSerie,
                    turno = turno,
                    anioProceso = s.anioProceso
                )
            )
        )
    }

    private fun update(reducer: (ReinspeccionUiState) -> ReinspeccionUiState) {
        _state.value = reducer(_state.value)
    }

    private fun send(event: UiEvent) {
        _events.trySend(event)
    }
}
