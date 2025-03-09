package com.example.pokemon_boxes.data.di

import android.content.Context
import androidx.room.Room
import com.example.pokemon_boxes.data.local.PokemonDatabase
import com.example.pokemon_boxes.data.repository.PokemonRepository
import com.example.pokemon_boxes.domain.repository.IPokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePokemonDatabase(@ApplicationContext context: Context): PokemonDatabase =
        Room.databaseBuilder(context, PokemonDatabase::class.java, PokemonDatabase.name).build()


    @Provides
    @Singleton
    fun providePokemonRepository(database: PokemonDatabase): IPokemonRepository =
        PokemonRepository(dao = database.dao)

}