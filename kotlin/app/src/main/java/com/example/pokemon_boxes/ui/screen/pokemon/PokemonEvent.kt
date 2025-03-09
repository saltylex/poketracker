package com.example.pokemon_boxes.ui.screen.pokemon

import java.util.Date

sealed interface PokemonEvent {
    data class NameChange(val value: String) : PokemonEvent
    data class TypeChange(val value: String) : PokemonEvent
    data class SpriteChange(val value: String) : PokemonEvent
    data class DateChange(val value: String) : PokemonEvent
    data class PlaceChange(val value: String) : PokemonEvent
    data class GameChange(val value: String) : PokemonEvent
    data class NotesChange(val value: String) : PokemonEvent
    data class CaughtChange(val value: Boolean) : PokemonEvent
    data class DexNoChange(val value: Int) : PokemonEvent
    object Save : PokemonEvent
    object NavigateBack : PokemonEvent
    object DeletePokemon : PokemonEvent
}