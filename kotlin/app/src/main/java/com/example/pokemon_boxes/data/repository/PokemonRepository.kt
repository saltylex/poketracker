package com.example.pokemon_boxes.data.repository

import android.util.Log
import com.example.pokemon_boxes.data.local.dao.PokemonDao
import com.example.pokemon_boxes.data.mapper.asExternalModel
import com.example.pokemon_boxes.data.mapper.toEntity
import com.example.pokemon_boxes.domain.model.Pokemon
import com.example.pokemon_boxes.domain.repository.IPokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PokemonRepository(private val dao: PokemonDao) : IPokemonRepository {
    override fun getAllPokemon(): Flow<List<Pokemon>> {
        Log.d("PokemonDb", dao.getAllPokemon().toString())
        return dao.getAllPokemon().map { pokemon -> pokemon.map { it.asExternalModel() } }
    }

    override suspend fun getPokemonById(id: Int): Pokemon? {
        return dao.getPokemonById(id)?.asExternalModel()
    }

    override suspend fun createPokemon(pokemon: Pokemon) {
        dao.createPokemon(pokemon.toEntity())
    }

    override suspend fun updatePokemon(pokemon: Pokemon) {
        dao.updatePokemon(pokemon.toEntity())
    }

    override suspend fun deletePokemon(pokemon: Int?) {
        dao.deletePokemon(pokemon)
    }
}