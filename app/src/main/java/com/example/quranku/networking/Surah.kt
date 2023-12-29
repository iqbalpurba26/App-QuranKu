package com.example.quranku.networking

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Surah(
    var arti:String?,
    val asma:String?,
    val nomor:String,
    val nama:String?,
    val type:String?
) : Parcelable
