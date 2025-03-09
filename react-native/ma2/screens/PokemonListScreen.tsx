import React, {useContext, useEffect, useState} from 'react';
import {View, FlatList, TouchableOpacity, Text, Button, ScrollView} from 'react-native';
import {RouteProp, useNavigation} from '@react-navigation/native';
import {Pokemon} from '../models/Pokemon';
import PokemonCard from '../components/PokemonCard';
import {StackNavigationProp} from "@react-navigation/stack";
import {RootStackParamList} from "../models/Routes";
import PokemonContext from "../contexts/PokemonContext";

type PokemonListScreenNavigationProp = StackNavigationProp<RootStackParamList>;

type PokemonListScreenRouteProp = RouteProp<RootStackParamList, 'PokemonList'>;

type Props = {
    navigation: PokemonListScreenNavigationProp;
    route: PokemonListScreenRouteProp;
};

const PokemonListScreen: React.FC<Props> = ({navigation}) => {

    const {pokemonList} = useContext(PokemonContext)

    const handlePokemonPress = (pokemon: Pokemon) => {
        // Navigate to detail/edit screen passing the Pokemon details
        navigation.navigate('PokemonDetail' as never, {pokemon} as never);
    };

    const handleAddPokemon = () => {
        navigation.navigate('PokemonDetail' as never, {pokemon: null} as never); // Navigate to add mode
    };

    return (
        <ScrollView>
            {pokemonList.map(pokemon => (
                <PokemonCard
                    key={pokemon.id}
                    pokemon={pokemon}
                    onPress={() => handlePokemonPress(pokemon)}
                />
            ))}
            {pokemonList.length === 0 && (
                <Text style={{textAlign: 'center', marginTop: 20}}>No Pokemon!</Text>
            )}
            <Button title="Add Pokemon" onPress={handleAddPokemon}/>
        </ScrollView>
    );
};

export default PokemonListScreen;
