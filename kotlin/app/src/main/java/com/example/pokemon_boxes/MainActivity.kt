package com.example.pokemon_boxes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemon_boxes.ui.screen.pokemon.PokemonScreen
import com.example.pokemon_boxes.ui.screen.pokemon.PokemonViewModel
import com.example.pokemon_boxes.ui.screen.pokemon_list.PokemonListScreen
import com.example.pokemon_boxes.ui.screen.pokemon_list.PokemonListViewModel
import com.example.pokemon_boxes.ui.theme.PokemonboxesTheme
import com.example.pokemon_boxes.ui.util.Route
import com.example.pokemon_boxes.ui.util.UIEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonboxesTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Route.pokemonList) {
                    composable(route = Route.pokemonList) {
                        val viewModel = hiltViewModel<PokemonListViewModel>()
                        val pokemonList by viewModel.pokemonList.collectAsStateWithLifecycle()

                        PokemonListScreen(
                            pokemonList = pokemonList,
                            onPokemonClick = {
                                navController.navigate(
                                    Route.pokemon.replace(
                                        "{id}",
                                        it.id.toString()
                                    )
                                )
                            },
                            onAddPokemonClick = { navController.navigate(Route.pokemon) }
                        )

                    }
                    composable(route = Route.pokemon) {
                        val viewModel = hiltViewModel<PokemonViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()
                        
                        LaunchedEffect(key1 = true){
                            viewModel.event.collect{
                                event -> when(event){
                                is UIEvent.NavigateBack -> navController.popBackStack()
                                else -> Unit
                            }
                            }
                        }
                        PokemonScreen(state = state, onEvent = viewModel::onEvent)
                    }
                }
            }

        }
    }
}
