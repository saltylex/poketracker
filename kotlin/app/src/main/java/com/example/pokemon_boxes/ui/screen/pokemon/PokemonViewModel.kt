package com.example.pokemon_boxes.ui.screen.pokemon

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon_boxes.domain.model.Pokemon
import com.example.pokemon_boxes.domain.repository.IPokemonRepository
import com.example.pokemon_boxes.ui.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: IPokemonRepository,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _state = MutableStateFlow(PokemonState())
    val state = _state.asStateFlow()

    // through this channel we listen to the events
    private val _event = Channel<UIEvent>()
    val event = _event.receiveAsFlow()

    private fun sendEvent(event: UIEvent) {
        viewModelScope.launch { _event.send(event) }
    }

    init {
        savedStateHandle.get<String>("id")?.let {
            val id = it.toInt()
            viewModelScope.launch {
                repository.getPokemonById(id)
                    ?.let { pokemon ->
                        _state.update { screenState ->
                            screenState.copy(
                                id = pokemon.id,
                                name = pokemon.name,
                                type = pokemon.type,
                                sprite = pokemon.sprite,
                                date = pokemon.date,
                                place = pokemon.place,
                                game = pokemon.game,
                                notes = pokemon.notes,
                                caught = pokemon.caught,
                                dexNo = pokemon.dexNo,
                            )
                        }
                    }
            }

        }
    }

    fun onEvent(event: PokemonEvent) {
        when (event) {

            is PokemonEvent.CaughtChange -> {
                _state.update { it.copy(caught = event.value) }
            }

            is PokemonEvent.DateChange -> {
                _state.update { it.copy(date = event.value) }
            }


            is PokemonEvent.DexNoChange -> {
                _state.update { it.copy(dexNo = event.value) }
            }

            is PokemonEvent.GameChange -> {
                _state.update { it.copy(game = event.value) }
            }

            is PokemonEvent.NameChange -> {
                _state.update { it.copy(name = event.value) }
            }

            PokemonEvent.NavigateBack -> sendEvent(UIEvent.NavigateBack)
            is PokemonEvent.NotesChange -> {
                _state.update { it.copy(notes = event.value) }
            }

            is PokemonEvent.PlaceChange -> {
                _state.update { it.copy(place = event.value) }
            }

            PokemonEvent.Save -> {
                viewModelScope.launch {
                    val state = state.value
                    val pokemon = Pokemon(
                        state.id,
                        state.name,
                        state.type,
                        state.sprite,
                        state.date,
                        state.place,
                        state.game,
                        state.notes,
                        state.caught,
                        state.dexNo
                    )
                    if (state.id == null) {
                        repository.createPokemon(pokemon)

                    } else {
                        repository.updatePokemon(pokemon)
                    }
                    sendEvent(UIEvent.NavigateBack)
                }
            }

            is PokemonEvent.SpriteChange -> {
                _state.update { it.copy(sprite = event.value) }
            }

            is PokemonEvent.TypeChange -> {
                _state.update { it.copy(type = event.value) }
            }

            PokemonEvent.DeletePokemon -> {
                viewModelScope.launch {
                    val state = state.value
                    val pokemon = state.id
                    repository.deletePokemon(pokemon)
                    Log.d("check","deleted pokemon with id" + state.id.toString())
                    sendEvent(UIEvent.NavigateBack)
                }
            }
        }
    }
}