package com.example.quranku.networking

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface QuranApi {
    companion object {
        const val BASE_URL="https://api.npoint.io"
    }

    @GET("/99c279bb173a6e28359c")
    fun getListSurah(): Call<SurahResponse>

    @GET("/99c279bb173a6e28359c/surat/{nomor}")
    fun getDetailSurah(
        @Path("nomor") nomor:String
    ) : Call<ArrayList<DetailSurah>>
}