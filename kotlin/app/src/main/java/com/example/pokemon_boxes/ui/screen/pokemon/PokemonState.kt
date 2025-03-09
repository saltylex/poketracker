package com.example.pokemon_boxes.ui.screen.pokemon


data class PokemonState(
    val id: Int? = null,
    val name: String = "",
    val type: String = "",
    val sprite: String = "",
    val date: String? = "",
    val place: String = "",
    val game: String = "",
    val notes: String = "",
    val caught: Boolean = false,
    val dexNo: Int = 0,
)
