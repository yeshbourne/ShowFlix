package com.yb.showflix.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yb.showflix.core.extension.load
import com.yb.showflix.databinding.FragmentShowsItemBinding
import com.yb.showflix.model.search.Show

class ShowItemRecyclerViewAdapter(
    private val values: List<Show>,
    private val itemListener: ShowItemListener
) : RecyclerView.Adapter<ShowItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(FragmentShowsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(values[position],itemListener)

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: FragmentShowsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Show, itemListener: ShowItemListener) {
            binding.poster.load(item.Poster)
            binding.title.text = item.Title
            binding.year.text = item.Year
            binding.showType.text = item.Type
            itemView.setOnClickListener {
                itemListener.onClickItemListener(item.imdbID)
            }
        }
    }

    interface ShowItemListener{
        fun onClickItemListener(imdbId: String)
    }
}