@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pokemon_boxes.ui.screen.pokemon_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pokemon_boxes.R
import com.example.pokemon_boxes.domain.model.Pokemon
import com.example.pokemon_boxes.ui.theme.TypeColors


@Composable
fun PokemonListItem(
    pokemon: Pokemon,
    onPokemonClick: (Pokemon) -> Unit
) {
    val pokemonCaughtState = remember{mutableStateOf(pokemon.caught)}
    val currentColor = when (pokemon.type) {
        "Psychic" -> TypeColors.PsychicType
        "Dark" -> TypeColors.DarkType
        "Fire" -> TypeColors.FireType
        "Grass" -> TypeColors.GrassType
        "Water" -> TypeColors.WaterType
        "Electric" -> TypeColors.ElectricType
        "Rock" -> TypeColors.RockType
        "Steel" -> TypeColors.SteelType
        "Ghost" -> TypeColors.GhostType
        "Ground" -> TypeColors.GroundType
        "Ice" -> TypeColors.IceType
        "Fairy" -> TypeColors.FairyType
        "Bug" -> TypeColors.BugType
        "Dragon" -> TypeColors.DragonType
        "Fighting" -> TypeColors.FightingType
        "Normal" -> TypeColors.NormalType
        "Poison" -> TypeColors.PoisonType
        "Flying" -> TypeColors.FlyingType
        else -> Color(0,0,0, 0)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onPokemonClick(pokemon) }
            .padding(5.dp).background(currentColor).alpha(when(pokemonCaughtState.value){
                true -> 0.9f
                false -> 0.4f
            }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(pokemon.sprite)
                .crossfade(true)
                .error(R.drawable.missingno)
                .build(),
            placeholder = painterResource(R.drawable.missingno),
            contentDescription = "image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.height(70.dp).width(70.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(text = pokemon.name, style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(bottom = 5.dp))
            Text(text = pokemon.type, style = MaterialTheme.typography.bodySmall)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    pokemonList: List<Pokemon>,
    onPokemonClick: (Pokemon) -> Unit,
    onAddPokemonClick: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddPokemonClick) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add Pokemon")

            }
        }
    ) { padding ->
        LazyColumn(
            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = 15.dp + padding.calculateTopPadding(),
                bottom = 15.dp + padding.calculateBottomPadding()
            )
        ) {
            item { Image(
                painter = painterResource(id = R.drawable.poketracker_photo), // Replace with your image resource
                contentDescription = "poketracker photo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .width(100.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            ) }
            items(pokemonList) { pokemon ->
                PokemonListItem(pokemon = pokemon, onPokemonClick = {onPokemonClick(pokemon)} )
            }
        }
    }
}