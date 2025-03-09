package com.example.pokemon_boxes.ui.util

sealed interface UIEvent{
    data class Navigate(val route: String): UIEvent
    object NavigateBack: UIEvent
}