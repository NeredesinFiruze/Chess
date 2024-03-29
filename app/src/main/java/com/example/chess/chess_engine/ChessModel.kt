package com.example.chess.chess_engine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chess.R
import com.example.chess.data.ChessRepository
import com.example.chess.data.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChessModel @Inject constructor(
    private val repository: ChessRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(EngineState())
    private val _boardState = MutableStateFlow(BoardState())
    val boardState = combine(_state, _boardState) { state, boardState ->
        boardState.copy(
            state = state
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), BoardState())

    init {
        this.onEvent(EngineEvent.NewGame)
    }

    fun onEvent(event: EngineEvent) {
        when (event) {
            is EngineEvent.ChoosePiece -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(
                        piece = event.piece
                    )
                    _boardState.collectLatest {
                        it.canMove = CanMove().canMove(event.piece, this@ChessModel)
                    }
                }
            }
            is EngineEvent.MoveTo -> {
                if (_state.value.piece != null) {
                    viewModelScope.launch {
                        val piece = _state.value.piece!!
                        val canMove = CanMove().canMove(piece, this@ChessModel)

                        _state.value = _state.value.copy(
                            toCol = event.col,
                            toRow = event.row
                        )

                        val move = "${_state.value.toCol}${_state.value.toRow}".toInt()
                        val condition = canMove.contains(move)

                        if (condition) {
                            _boardState.collect { setBoard ->

                                val isWhite = piece.player == Player.WHITE

                                setBoard.board.remove(_state.value.piece)
                                val enemyPiece = pieceAt(_state.value.toCol!!, _state.value.toRow!!)
                                if (enemyPiece != null) {
                                    setBoard.board.remove(enemyPiece)
                                }

                                val king = piece.rank == Rank.KING
                                val pawn = piece.rank == Rank.PAWN
                                if (king && _state.value.toRow in listOf(3,7)) {
                                    canCastle()
                                }
                                else if (pawn &&
                                    _state.value.toRow != piece.row &&
                                    pieceAt(_state.value.toCol!!, _state.value.toRow!!) == null
                                ){
                                    enPassantRule()
                                }
                                else {
                                    setBoard.board.add(
                                        Pieces(
                                            _state.value.toCol!!,
                                            _state.value.toRow!!,
                                            piece.rank,
                                            piece.player
                                        )
                                    )
                                }

                                setBoard.canMove = arrayListOf()
                                setBoard.turn =
                                    if (piece.player == Player.WHITE) Player.BLACK else Player.WHITE
                                _state.value = _state.value.copy(piece = null)

                                val rank = when (piece.rank) {
                                    Rank.ROOK -> if (isWhite) "R" else "r"
                                    Rank.BISHOP -> if (isWhite) "B" else "b"
                                    Rank.PAWN -> if (isWhite) "P" else "p"
                                    Rank.KING -> if (isWhite) "K" else "k"
                                    Rank.QUEEN -> if (isWhite) "Q" else "q"
                                    Rank.KNIGHT -> if (isWhite) "N" else "n"
                                }
                                _boardState.value.moveList.add("$rank${piece.col}${piece.row}${_state.value.toCol}${_state.value.toRow}")
                                repository.insertMove(Game(_boardState.value.moveList, 123456789))
                            }
                        } else {
                            _state.value = _state.value.copy(
                                piece = null,
                            )
                            _boardState.collectLatest {
                                it.canMove = arrayListOf()
                            }
                        }
                    }
                } else return
            }
            is EngineEvent.NewGame -> {
                viewModelScope.launch {

                    if (_boardState.value.moveList.isNotEmpty()) {
                        repository.deleteGame(Game(_boardState.value.moveList, 123456789))
                        _boardState.value.moveList = arrayListOf()
                    }

                    _boardState.collectLatest {

                        it.turn = Player.WHITE
                        it.canMove = arrayListOf()
                        it.board = arrayListOf()

                        val setToBoard = it.board

                        for (i in 1..8) {
                            setToBoard.add(Pieces(2, i, Rank.PAWN, Player.WHITE))
                            setToBoard.add(Pieces(7, i, Rank.PAWN, Player.BLACK))
                        }
                        for (i in 0..1) {
                            setToBoard.add(Pieces(1, if (i * 8 == 0) 1 else i * 8, Rank.ROOK, Player.WHITE))
                            setToBoard.add(Pieces(8, if (i * 8 == 0) 1 else i * 8, Rank.ROOK, Player.BLACK))

                            setToBoard.add(Pieces(1, i * 5 + 2, Rank.KNIGHT, Player.WHITE))
                            setToBoard.add(Pieces(8, i * 5 + 2, Rank.KNIGHT, Player.BLACK))

                            setToBoard.add(Pieces(1, i * 3 + 3, Rank.BISHOP, Player.WHITE))
                            setToBoard.add(Pieces(8, i * 3 + 3, Rank.BISHOP, Player.BLACK))
                        }
                        setToBoard.add(Pieces(1, 4, Rank.QUEEN, Player.WHITE))
                        setToBoard.add(Pieces(8, 4, Rank.QUEEN, Player.BLACK))

                        setToBoard.add(Pieces(1, 5, Rank.KING, Player.WHITE))
                        setToBoard.add(Pieces(8, 5, Rank.KING, Player.BLACK))
                    }
                }
            }
        }
    }

    private fun canCastle() {
        val setBoard = _boardState.value
        val piece = _state.value.piece!!
        val isWhite = piece.player == Player.WHITE

        if (isWhite && _state.value.toRow == 7) {
            setBoard.board.add(
                Pieces(
                    _state.value.toCol!!,
                    _state.value.toRow!!,
                    piece.rank,
                    piece.player
                )
            )
            setBoard.board.remove(Pieces(1, 8, Rank.ROOK, Player.WHITE))
            setBoard.board.add(Pieces(1, 6, Rank.ROOK, Player.WHITE))
        } else if (isWhite && _state.value.toRow == 3) {
            setBoard.board.add(
                Pieces(
                    _state.value.toCol!!,
                    _state.value.toRow!!,
                    piece.rank,
                    piece.player
                )
            )
            setBoard.board.remove(Pieces(1, 1, Rank.ROOK, Player.WHITE))
            setBoard.board.add(Pieces(1, 4, Rank.ROOK, Player.WHITE))
        } else if (!isWhite && _state.value.toRow == 7) {
            setBoard.board.add(
                Pieces(
                    _state.value.toCol!!,
                    _state.value.toRow!!,
                    piece.rank,
                    piece.player
                )
            )
            setBoard.board.remove(Pieces(8, 8, Rank.ROOK, Player.BLACK))
            setBoard.board.add(Pieces(8, 6, Rank.ROOK, Player.BLACK))
        } else if (!isWhite && _state.value.toRow == 3) {
            setBoard.board.add(
                Pieces(
                    _state.value.toCol!!,
                    _state.value.toRow!!,
                    piece.rank,
                    piece.player
                )
            )
            setBoard.board.remove(Pieces(8, 1, Rank.ROOK, Player.BLACK))
            setBoard.board.add(Pieces(8, 4, Rank.ROOK, Player.BLACK))
        }
    }

    private fun enPassantRule(){

        val setBoard = _boardState.value
        val piece = _state.value.piece!!
        val isWhite = piece.player == Player.WHITE

        setBoard.board.add(
            Pieces(
                _state.value.toCol!!,
                _state.value.toRow!!,
                piece.rank,
                piece.player
            )
        )
        setBoard.board.remove(
            Pieces(
                if (isWhite)_state.value.toCol!! - 1 else _state.value.toCol!! + 1,
                _state.value.toRow!!,
                Rank.PAWN,
                if (isWhite) Player.BLACK else Player.WHITE
            ))
    }

    fun pieceAt(col: Int, row: Int): Pieces? {
        for (piece in _boardState.value.board) {
            if (col == piece.col && row == piece.row) {
                return piece
            }
        }
        return null
    }

    fun piecePicture(col: Int, row: Int): Int? {
        val piece = pieceAt(col, row)
        val white = piece?.player == Player.WHITE

        if (piece != null) {
            when (piece.rank) {
                Rank.ROOK -> {
                    return if (white) R.drawable.w_rook
                    else R.drawable.b_rook
                }
                Rank.BISHOP -> {
                    return if (white) R.drawable.w_bishop
                    else R.drawable.b_bishop
                }
                Rank.PAWN -> {
                    return if (white) R.drawable.w_pawn
                    else R.drawable.b_pawn
                }
                Rank.KING -> {
                    return if (white) R.drawable.w_king
                    else R.drawable.b_king
                }
                Rank.QUEEN -> {
                    return if (white) R.drawable.w_queen
                    else R.drawable.b_queen
                }
                Rank.KNIGHT -> {
                    return if (white) R.drawable.w_knight
                    else R.drawable.b_knight
                }
            }
        }
        return null
    }
}