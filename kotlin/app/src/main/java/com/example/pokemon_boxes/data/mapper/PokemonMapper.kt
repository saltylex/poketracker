package com.example.pokemon_boxes.data.mapper

import com.example.pokemon_boxes.data.local.entity.PokemonEntity
import com.example.pokemon_boxes.domain.model.Pokemon

fun PokemonEntity.asExternalModel(): Pokemon = Pokemon(
    id,
    name,
    type,
    sprite,
    date,
    place,
    game,
    notes,
    caught,
    dexNo
)

fun Pokemon.toEntity(): PokemonEntity = PokemonEntity(
    id,
    name,
    type,
    sprite,
    date,
    place,
    game,
    notes,
    caught,
    dexNo
)