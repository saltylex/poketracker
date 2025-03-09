package com.example.pokemon_boxes.domain.model


// for easier access to the ui levels
data class Pokemon(
    val id: Int? = null,
    val name: String = "",
    val type: String = "",
    val sprite: String = "",
    val date: String? ="",
    val place: String = "",
    val game: String = "",
    val notes: String = "",
    val caught: Boolean = false,
    val dexNo: Int = 0,
)
