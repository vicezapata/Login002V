package com.example.login002v.viewmodel


sealed class UiEvent {
    data class ShowToast(val message: String) : UiEvent()
    data class NavigateNext(val payload: ReinspeccionPayload) : UiEvent()
}

data class ReinspeccionPayload(
    val dia: Int,
    val empresa: String,
    val capacidadKg: Double,
    val ultimaSerie: String,
    val turno: Int,
    val anioProceso: Int
)