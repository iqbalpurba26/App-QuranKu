package com.example.quranku.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface FavoriteDao {
    @Insert
    fun addtoFavorite(favorite:Favorite)
    @Query("SELECT * FROM favSurah")
    fun getFavorite():LiveData<List<Favorite>>

    @Query("SELECT * FROM favSurah where nomor=:nomor")
    fun getFavoriteId(nomor:String):Favorite?

    @Query("DELETE FROM favSurah where nomor=:nomor")
    fun deleteFavorite(nomor: String):Int

}