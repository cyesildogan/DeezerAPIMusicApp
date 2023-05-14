package com.example.deezerapimusicapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.deezerapimusicapp.R
import com.example.deezerapimusicapp.databinding.AritstsListRecyclerRowBinding
import com.example.deezerapimusicapp.model.artist.ArtistData
import com.example.deezerapimusicapp.util.loadUrl

class ArtistListAdapter(
    private val artistList: List<ArtistData>,
    private val onClickListener: OnClickListener
) : ListAdapter<ArtistData, ArtistListAdapter.ViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.aritsts_list_recycler_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return artistList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(artistList[position])
        holder.itemView.setOnClickListener {
            onClickListener.clickListener(artistList[position])
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = AritstsListRecyclerRowBinding.bind(itemView)
        fun bind(artist: ArtistData){
            artist.name.let {
                binding.artistListRecyclerArtistsNameText.text = it
            }
            artist.picture_medium.let {
                binding.artistListRecyclerArtistImageView.loadUrl(it)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ArtistData>() {
        override fun areItemsTheSame(oldItem: ArtistData, newItem: ArtistData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ArtistData, newItem: ArtistData): Boolean {
            return oldItem == newItem
        }
    }

    class OnClickListener(val clickListener: (artistList: ArtistData) -> Unit) {
        fun onClick(artist: ArtistData) = clickListener(artist)
    }
}