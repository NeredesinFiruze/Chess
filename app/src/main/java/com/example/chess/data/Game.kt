package com.example.chess.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Game(
    val board: List<String>,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
