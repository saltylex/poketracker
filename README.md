# 🐀 PokéTracker App

<img width="1024" alt="poketracker" src="https://github.com/user-attachments/assets/cd74a3c6-58d0-40f9-b466-ec409c9d7942">

## Overview
This project showcases a multi-platform mobile application with CRUD functionalities, using both native and cross-platform technologies and having a backend integration with a Django server. Specifically designed for users that want to manage their Pokemon collections across multiple games.

## Technologies

- **Backend**: Django
- **Database**: Room, SQLite
- **Frontend**: React Native, Kotlin
- **Other**: Hilt, Websockets 

## Features

- CRUD operations for Pokemon entities
- Real-time updates of data through websockets
- Offline support if server is unreachable, using local storage
- Cross-platform for Android & iOS

## Installation & Setup

### React Native:

#### 1. Clone the repository:
```bash
git clone https://github.com/saltylex/pokemon-boxes.git
cd pokemon-boxes/react-native-app
```

#### 2. Install dependencies:
```bash
npm install
```

#### 3. Run the application:
```bash
npx expo start
```

### Kotlin:

#### 1. Open the project in Android Studio:
```bash

cd pokemon-boxes/kotlin-app
````

#### 2. Build and run the app on an Android device or emulator.

### Django Server

#### 1. Navigate to the server directory:
```bash
cd pokemon-boxes/django-server
```

#### 2. Run the server:
```bash
python manage.py runserver
```

## License

This project is licensed under the GNU General Public License v3.0 - see the [LICENSE](./LICENSE) file for details.
