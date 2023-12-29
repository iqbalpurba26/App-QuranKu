package com.example.quranku.fragment.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quranku.R
import com.example.quranku.adapter.DisukaiAdapter
import com.example.quranku.adapter.SurahAdapter
import com.example.quranku.databinding.FragmentDisukaiBinding
import com.example.quranku.fragment.quran.FragmentListSurahDirections
import com.example.quranku.networking.Surah
import com.example.quranku.room.Favorite
import com.example.quranku.room.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentLike: Fragment(R.layout.fragment_disukai) {
    private lateinit var binding: FragmentDisukaiBinding
    lateinit var disukaiAdapter: DisukaiAdapter
    private var favoriteList = arrayListOf<Favorite>()

    private val viewModel by viewModels<FavoriteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDisukaiBinding.bind(view)
        getSurah()
    }

    fun getSurah() {
        disukaiAdapter = DisukaiAdapter(favoriteList, object : DisukaiAdapter.setOnClikListerner {
            override fun itemClick(surah: Favorite) {
                var surah = Surah(surah?.arti, surah?.asma, surah.nomor, surah?.type, surah?.nama)
                var action =
                    FragmentListSurahDirections.actionNavigationQuranToNavigationDetail(surah)
                findNavController().navigate(action)
            }

            override fun addToFavorite(surah: Favorite) {

            }

        }, viewModel)
        binding.rvLike.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLike.adapter = disukaiAdapter
        viewModel.getFavorite().observe(viewLifecycleOwner){
            disukaiAdapter.setList(it)
        }
    }
}