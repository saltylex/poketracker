import React, { useState } from 'react';
import {View, Text, TextInput, Button, Image, Switch} from 'react-native';
import { Pokemon } from '../models/Pokemon';

const PokemonForm: React.FC<{
    pokemon: Pokemon,
    onSave: (updatedPokemon: Pokemon) => void,
    onDelete?: () => void,
}> = ({ pokemon, onSave, onDelete }) => {
    const [name, setName] = useState(pokemon.name);
    const [type, setType] = useState(pokemon.type);
    const [sprite, setSprite] = useState(pokemon.sprite);
    const [date, setDate] = useState(pokemon.date);
    const [place, setPlace] = useState(pokemon.place);
    const [game, setGame] = useState(pokemon.game);
    const [notes, setNotes] = useState(pokemon.notes);
    const [caught, setCaught] = useState(pokemon.caught);
    const [dexNo, setDexNo] = useState(pokemon.dexNo.toString());

    const handleSave = () => {
        const updatedPokemon: Pokemon = {
            ...pokemon,
            name,
            type,
            sprite,
            date,
            place,
            game,
            notes,
            caught,
            dexNo: parseInt(dexNo),
        };
        onSave(updatedPokemon);
    };

    const handleDelete = () => {
        onDelete()
    };

    return (
        <View style={{ margin: 20 }}>
            <Image
                source={{ uri: sprite }}
                style={{ width: 100, height: 100, marginBottom: 10, left: 100, }}
            />
            <Text>Name:</Text>
            <TextInput
                value={name}
                onChangeText={setName}
                placeholder="Enter Name"
                style={{ borderWidth: 1, padding: 10, marginBottom: 10 }}
            />

            <Text>Type:</Text>
            <TextInput
                value={type}
                onChangeText={setType}
                placeholder="Enter Type"
                style={{ borderWidth: 1, padding: 10, marginBottom: 10 }}
            />

            <Text>Sprite (URL):</Text>
            <TextInput
                value={sprite}
                onChangeText={setSprite}
                placeholder="Enter Sprite URL"
                style={{ borderWidth: 1, padding: 10, marginBottom: 10 }}
            />

            <Text>Date:</Text>
            <TextInput
                value={date}
                onChangeText={setDate}
                placeholder="Enter Date"
                style={{ borderWidth: 1, padding: 10, marginBottom: 10 }}
            />

            <Text>Place:</Text>
            <TextInput
                value={place}
                onChangeText={setPlace}
                placeholder="Enter Place"
                style={{ borderWidth: 1, padding: 10, marginBottom: 10 }}
            />

            <Text>Game:</Text>
            <TextInput
                value={game}
                onChangeText={setGame}
                placeholder="Enter Game"
                style={{ borderWidth: 1, padding: 10, marginBottom: 10 }}
            />

            <Text>Notes:</Text>
            <TextInput
                value={notes}
                onChangeText={setNotes}
                placeholder="Enter Notes"
                style={{ borderWidth: 1, padding: 10, marginBottom: 10 }}
            />

            <View style={{ flexDirection: 'row', alignItems: 'center', marginBottom: 10 }}>
            <Text>Caught:</Text>
            <Switch
                value={caught}
                onValueChange={(value) => setCaught(value)}
            />
            </View>

            <Text>Dex No:</Text>
            <TextInput
                value={dexNo}
                onChangeText={setDexNo}
                placeholder="Enter Dex No"
                keyboardType="numeric"
                style={{ borderWidth: 1, padding: 10, marginBottom: 10 }}
            />

            <Button title="Save" onPress={handleSave} />

            {onDelete && pokemon.id ? (
                <Button title="Delete" onPress={handleDelete} color="red" />
            ) : null}
        </View>
    );
};

export default PokemonForm;
