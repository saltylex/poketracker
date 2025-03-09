package com.example.pokemon_boxes.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pokemon_boxes.data.local.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM PokemonEntity")
    fun getAllPokemon(): Flow<List<PokemonEntity>>

    @Query(
        """SELECT * FROM PokemonEntity
            WHERE id = :id"""
    )
    suspend fun getPokemonById(id: Int): PokemonEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createPokemon(pokemonEntity: PokemonEntity)

    @Query("DELETE FROM PokemonEntity WHERE id = :pokemonEntity")
    suspend fun deletePokemon(pokemonEntity: Int?)

    @Update
    suspend fun updatePokemon(pokemonEntity: PokemonEntity)
}