package com.example.quranku.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quranku.databinding.ListAyatBinding
import com.example.quranku.networking.DetailSurah

class DetailSurahAdapter(private val dataSet: ArrayList<DetailSurah>) :
    RecyclerView.Adapter<DetailSurahAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ListAyatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(surat: DetailSurah) {

            binding.tvNomorAyat.text = surat.nomor
            binding.tvAyat.text = surat.ar
            binding.tvTerjemahan.text = surat.id

        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val binding = ListAyatBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup,false)
        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        var surat = dataSet[position]
        viewHolder.bind(surat)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun setList(newList:List<DetailSurah>){
        dataSet.clear()
        dataSet.addAll(newList)
        notifyDataSetChanged()
    }


}
