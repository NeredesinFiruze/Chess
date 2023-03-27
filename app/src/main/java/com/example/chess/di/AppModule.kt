package com.example.chess.di

import android.app.Application
import androidx.room.Room
import com.example.chess.data.ChessDatabase
import com.example.chess.data.ChessDatabase.Companion.DATABASE_NAME
import com.example.chess.data.ChessRepository
import com.example.chess.data.ChessRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): ChessDatabase{
        return Room.databaseBuilder(
            app,
            ChessDatabase::class.java,
            DATABASE_NAME
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideRepository(database: ChessDatabase): ChessRepository{
        return ChessRepositoryImpl(database.dao)
    }
}