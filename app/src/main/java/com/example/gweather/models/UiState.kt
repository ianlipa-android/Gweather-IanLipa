package com.example.gweather.models

sealed class UiState {
    
    data object Loading : UiState()
    data class Success(val data: Any) : UiState()
    data class Error(val message: String) : UiState()
    data object Empty : UiState()

}