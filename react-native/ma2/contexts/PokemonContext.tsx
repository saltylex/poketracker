import React, {createContext, useState, useEffect, useContext} from 'react';
import {Pokemon} from '../models/Pokemon';
import {
    getAllPokemon,
    addPokemonService,
    updatePokemonService,
    deletePokemonService
} from '../utils/PokemonService';

type PokemonContextType = {
    pokemonList: Pokemon[];
    addPokemon: (pokemon: Pokemon) => void;
    updatePokemon: (updatedPokemon: Pokemon) => void;
    deletePokemon: (id: number) => void;
};

const PokemonContext = createContext<PokemonContextType>({
    pokemonList: [],
    addPokemon: () => {
    },
    updatePokemon: () => {
    },
    deletePokemon: () => {
    },
});

export const PokemonProvider: React.FC = ({children}) => {
    const [pokemonList, setPokemonList] = useState<Pokemon[]>([]);

    useEffect(() => {
        async function fetchPokemonData() {
            const allPokemon = getAllPokemon();
            setPokemonList(await allPokemon);
        }
        fetchPokemonData().then();
    }, []);

    console.log(pokemonList)
    const addPokemon = (pokemon: Pokemon) => {
        addPokemonService(pokemon).then();
        setPokemonList(prevList => [...prevList, pokemon]);
    };

    const updatePokemon = (updatedPokemon: Pokemon) => {
        updatePokemonService(updatedPokemon).then();
        setPokemonList(prevList => prevList.map(p => (p.id === updatedPokemon.id ? updatedPokemon : p)));
    };

    const deletePokemon = (id: number) => {
        deletePokemonService(id).then();
        setPokemonList(prevList => prevList.filter(p => p.id !== id));
    };

    return (
        <PokemonContext.Provider value={{pokemonList, addPokemon, updatePokemon, deletePokemon}}>
            {children}
        </PokemonContext.Provider>
    );
};

export default PokemonContext;
