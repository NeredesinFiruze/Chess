package com.example.chess.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.chess.util.Converter

@Database(entities = [Game::class], version = 1)
@TypeConverters(Converter::class)
abstract class ChessDatabase: RoomDatabase() {

    abstract val dao: GameDao

    companion object{
        const val DATABASE_NAME = "game_storage"
    }
}