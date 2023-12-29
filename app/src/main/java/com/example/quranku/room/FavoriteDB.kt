package com.example.quranku.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Favorite::class], version=1, exportSchema = false)
open abstract class FavoriteDB: RoomDatabase() {
    abstract fun favoriteDao():FavoriteDao
}