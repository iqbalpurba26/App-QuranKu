package com.example.quranku.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import org.jetbrains.annotations.NotNull

@Entity(tableName = "favSurah")
@Parcelize
data class Favorite(
    val arti:String?, val asma:String?,
    @PrimaryKey
    @NotNull
    val nomor:String, val nama:String?, val type:String?
) : Parcelable {
}

