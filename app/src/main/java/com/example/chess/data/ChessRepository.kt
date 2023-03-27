package com.example.chess.data

interface ChessRepository {

    suspend fun insertMove(game: Game)

    suspend fun deleteGame(game: Game)
}