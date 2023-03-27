package com.example.chess.data

class ChessRepositoryImpl(private val dao: GameDao): ChessRepository{
    override suspend fun insertMove(game: Game) {
        return dao.insertMove(game)
    }

    override suspend fun deleteGame(game: Game) {
        return dao.deleteGame(game)
    }
}