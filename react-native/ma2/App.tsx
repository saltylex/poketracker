import {StatusBar} from 'expo-status-bar';
import {SafeAreaView, StyleSheet, Text, View, Image} from 'react-native';
import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import PokemonListScreen from './screens/PokemonListScreen';
import PokemonDetailScreen from './screens/PokemonDetailScreen';
import PokemonContext, {PokemonProvider} from "./contexts/PokemonContext";
import 'react-native-gesture-handler'

const Stack = createStackNavigator();

export default function App() {
    return (
        <PokemonProvider>
        <NavigationContainer>
            <Stack.Navigator initialRouteName="PokemonList">
                <Stack.Screen
                    name="PokemonList"
                    component={PokemonListScreen}
                    options={{ title: 'Pokemon List' }}
                />
                <Stack.Screen
                    name="PokemonDetail"
                    component={PokemonDetailScreen}
                    options={{ title: 'Pokemon Detail' }}
                />
            </Stack.Navigator>
        </NavigationContainer>
        </PokemonProvider>
    );
}

const styles = StyleSheet.create({
        container: {
            flex: 1,
            backgroundColor: '#fff',
            alignItems: 'center',
            justifyContent: 'center',
        },
        title:
            {
                width: 300, height: 90, position: "absolute", top: 20,

            },
    }
);
