package com.example.quranku.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.quranku.databinding.ListSurahBinding
import com.example.quranku.networking.Surah
import com.example.quranku.room.FavoriteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SurahAdapter(private val dataSet: ArrayList<Surah>, val setOnItemClick:setOnClikListerner, private val viewModel: FavoriteViewModel) :
    RecyclerView.Adapter<SurahAdapter.ViewHolder>() {


    inner class ViewHolder(var binding: ListSurahBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(surat: Surah) {
            binding.tvNameSurahIndo.text = surat.nama
            binding.tvTipeSurah.text = surat.type
            binding.tvNoSurah.text = surat.nomor
            binding.tvNameSurahArab.text = surat.asma

            var isChecked = false

            CoroutineScope(Dispatchers.IO).launch{
                var count = viewModel.getFavoriteId(surat.nomor)
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
                        binding.like.isChecked = false
                        viewModel.deleteFavorite(data.nomor)
                    } else {
                        binding.like.isChecked = true
                        viewModel.addtoFavorite(data)
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

        val binding = ListSurahBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup,false)
        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        var surat = dataSet[position]
        viewHolder.bind(surat)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun setList(newList:List<Surah>){
        dataSet.clear()
        dataSet.addAll(newList)
        notifyDataSetChanged()
    }

    interface setOnClikListerner{
        fun itemClick(quran: Surah)
        fun addToFavorite(surah : Surah)
    }


}