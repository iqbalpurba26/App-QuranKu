package com.example.quranku.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.room.PrimaryKey
import com.example.quranku.networking.Surah
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.annotations.NotNull
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private var repository: FavoriteRepository): ViewModel() {
    fun addtoFavorite(surah: Surah){
        CoroutineScope(Dispatchers.IO).launch {
            val surah=Favorite(surah.arti,surah.asma,surah.nomor, surah.nama, surah.type)
            repository.addtoFavorite(surah)

        }

    }
    fun getFavorite(): LiveData<List<Favorite>> {
        return repository.getFavorite()
    }
    suspend fun getFavoriteId(id:String):Favorite?{
        return repository.getFavoriteId(id)
    }
    fun deleteFavorite(id:String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteFavorite(id)
        }
    }
}