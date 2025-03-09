package com.example.pokemon_boxes.domain.repository

import com.example.pokemon_boxes.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {

    fun getAllPokemon(): Flow<List<Pokemon>>
    suspend fun getPokemonById(id: Int): Pokemon?
    suspend fun createPokemon(pokemon: Pokemon)
    suspend fun updatePokemon(pokemon: Pokemon)
    suspend fun deletePokemon(pokemon: Int?)
}