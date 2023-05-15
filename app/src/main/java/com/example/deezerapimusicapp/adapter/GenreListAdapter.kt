package com.example.deezerapimusicapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.deezerapimusicapp.R
import com.example.deezerapimusicapp.databinding.CategoriesRecyclerRowBinding
import com.example.deezerapimusicapp.model.genre.GenreData
import com.example.deezerapimusicapp.util.loadUrl


class GenreListAdapter(
    private val genreList: List<GenreData>, private val onClickListener: OnClickListener
) : ListAdapter<GenreData, GenreListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.categories_recycler_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(genreList[position])
        holder.itemView.setOnClickListener {
            onClickListener.clickListener(genreList[position])

        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CategoriesRecyclerRowBinding.bind(itemView)
        fun bind(genre: GenreData) {
            genre.picture_medium.let {
                binding.categoriesRecyclerCategoriesImageView.loadUrl(it)
            }
            genre.name.let {
                binding.categoriesRecyclerCategoriesNameTextView.text = it
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<GenreData>() {
        override fun areItemsTheSame(oldItem: GenreData, newItem: GenreData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GenreData, newItem: GenreData): Boolean {
            return oldItem == newItem
        }
    }

    class OnClickListener(val clickListener: (genreList: GenreData) -> Unit) {
        fun onClick(genre: GenreData) = clickListener(genre)
    }
}