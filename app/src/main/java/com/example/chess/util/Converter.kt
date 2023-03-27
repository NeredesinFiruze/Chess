package com.example.chess.util

import androidx.room.TypeConverter

class Converter {

    @TypeConverter
    fun fromList(list: List<String>): String{
        return list.toString()
    }

    @TypeConverter
    fun toList(string: String): List<String>{
        return string.split(',').map {
            if (it.contains('[')) it.drop(1)
            else if (it.contains(']')) it.dropLast(1) else it
        }
    }
}