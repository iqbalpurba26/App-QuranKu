package com.example.quranku.fragment.quran

import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quranku.R
import com.example.quranku.adapter.DetailSurahAdapter
import com.example.quranku.databinding.FragmentDetailSurahBinding
import com.example.quranku.networking.DetailSurah
import com.example.quranku.networking.RetrofitClient
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@AndroidEntryPoint
class FragmentDetailSurah: Fragment(R.layout.fragment_detail_surah) {

    private lateinit var binding:FragmentDetailSurahBinding
    private lateinit var detailAdapter : DetailSurahAdapter
    private  var surahList = arrayListOf<DetailSurah>()
    private val args by navArgs<FragmentDetailSurahArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailSurahBinding.bind(view)
        getSurah()

    }


    fun getSurah(){
        detailAdapter = DetailSurahAdapter(surahList)
        binding.rvAyat.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAyat.adapter = detailAdapter

        var nomor = args.surah?.nomor.toString()
        RetrofitClient.apiService.getDetailSurah(nomor).enqueue(object:
            Callback<ArrayList<DetailSurah>> {
            override fun onResponse(call: Call<ArrayList<DetailSurah>>, response: Response<ArrayList<DetailSurah>>) {
                if(response.isSuccessful){
                    val quran = response.body()
                    if(quran!=null){
                        detailAdapter.setList(quran)
                        detailAdapter.notifyDataSetChanged()
                    }
                }

            }

            override fun onFailure(call: Call<ArrayList<DetailSurah>>, t: Throwable) {

            }

        })

    }




}