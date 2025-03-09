package com.example.pokemon_boxes.data.local.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey val id: Int?,
    val name: String,
    val type: String,
    val sprite: String,
    val date: String?,
    val place: String,
    val game: String,
    val notes: String,
    val caught: Boolean,
    val dexNo: Int,
)
