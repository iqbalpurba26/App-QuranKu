package com.example.quranku.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.quranku.databinding.ListDisukaiBinding
import com.example.quranku.databinding.ListSurahBinding
import com.example.quranku.networking.Surah
import com.example.quranku.room.Favorite
import com.example.quranku.room.FavoriteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DisukaiAdapter(private val dataSet: ArrayList<Favorite>, val setOnItemClick:setOnClikListerner, private val viewModel: FavoriteViewModel) :
    RecyclerView.Adapter<DisukaiAdapter.ViewHolder>() {


    inner class ViewHolder(var binding: ListDisukaiBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(surat: Favorite) {
            binding.tvNameSurahIndo.text = surat.nama
            binding.tvTipeSurah.text = surat.type
            binding.tvNoSurah.text = surat.nomor
            binding.tvNameSurahArab.text = surat.asma

            var isChecked = false

            CoroutineScope(Dispatchers.IO).launch{
                var count = surat.nomor
                withContext(Dispatchers.Main){
                    if(count==null){
                        isChecked = false
                        binding.like.isChecked = false
                    } else {
                        isChecked = true
                        binding.like.isChecked = true
                    }
                }
            }

            binding.like.setOnClickListener{
                var position = adapterPosition
                if(position!= RecyclerView.NO_POSITION){
                    var data = dataSet[position]
                    if(isChecked==true){
                        isChecked = false
                        binding.like.isChecked = false
                        viewModel.deleteFavorite(data.nomor)
                    }
                }
            }

            binding.root.setOnClickListener{
                var position = adapterPosition
                if(position!= RecyclerView.NO_POSITION){
                    var data = dataSet[position]
                    setOnItemClick.itemClick(data)
                }
            }


        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val binding = ListDisukaiBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup,false)
        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        var surat = dataSet[position]
        viewHolder.bind(surat)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun setList(newList:List<Favorite>){
        dataSet.clear()
        dataSet.addAll(newList)
        notifyDataSetChanged()
    }

    interface setOnClikListerner{
        fun itemClick(quran: Favorite)
        fun addToFavorite(surah : Favorite)
    }


}