package com.example.pokemon_boxes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokemon_boxes.data.local.dao.PokemonDao
import com.example.pokemon_boxes.data.local.entity.PokemonEntity

@Database(version = 1, entities = [PokemonEntity::class])
abstract class PokemonDatabase: RoomDatabase() {
    abstract val dao: PokemonDao;

    companion object {
        const val name = "pokemon_db"
    }
}