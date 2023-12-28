package com.example.quranku.fragment.quran

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quranku.R
import com.example.quranku.adapter.SurahAdapter
import com.example.quranku.databinding.FragmentQuranBinding
import com.example.quranku.networking.RetrofitClient
import com.example.quranku.networking.Surah
import com.example.quranku.networking.SurahResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentListSurah: Fragment(R.layout.fragment_quran) {

    private lateinit var binding:FragmentQuranBinding
    private lateinit var surahAdapter:SurahAdapter
    private var surahList = arrayListOf<Surah>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentQuranBinding.bind(view)
        getSurah()
    }

    fun getSurah(){
        surahAdapter = SurahAdapter(surahList, object:SurahAdapter.setOnClikListerner{
            override fun itemClick(surah: Surah) {
                var surah = Surah(surah?.arti, surah?.asma, surah?.nomor, surah?.type, surah?.nama)
                var action = FragmentListSurahDirections.actionNavigationQuranToNavigationDetail(surah)
                findNavController().navigate(action)
            }

        })

        binding.rvSurah.layoutManager = LinearLayoutManager(requireContext())

        binding.rvSurah.adapter = surahAdapter

        RetrofitClient.apiService.getListSurah().enqueue(object: Callback<SurahResponse> {
            override fun onResponse(call: Call<SurahResponse>, response: Response<SurahResponse>) {
                if(response.isSuccessful){
                    val surah = response.body()
                    if(surah!=null){
                        surahAdapter.setList(surah.data)
                        surahAdapter.notifyDataSetChanged()
                    }
                }

            }

            override fun onFailure(call: Call<SurahResponse>, t: Throwable) {

            }

        })

    }



}