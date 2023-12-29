package com.example.quranku.di

import android.content.Context
import androidx.room.Room
import com.example.quranku.room.FavoriteDB
import com.example.quranku.room.FavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

object AppModule {
    @Singleton
    @Provides
    fun providesFavorite(@ApplicationContext app: Context): FavoriteDB = Room.databaseBuilder(
        app,FavoriteDB::class.java,"favSurah").build()

    @Singleton
    @Provides
    fun providesFavoriteDao(db: FavoriteDB):FavoriteDao=db.favoriteDao()
}