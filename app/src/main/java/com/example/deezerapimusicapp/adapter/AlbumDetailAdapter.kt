package com.example.deezerapimusicapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.deezerapimusicapp.R
import com.example.deezerapimusicapp.databinding.AlbumDetailsRecyclerRowBinding
import com.example.deezerapimusicapp.databinding.ArtistDetailsRecyclerRowBinding
import com.example.deezerapimusicapp.model.albumDetail.Album
import com.example.deezerapimusicapp.model.albumDetail.AlbumDetailsModel
import com.example.deezerapimusicapp.model.albumDetail.DataX
import com.example.deezerapimusicapp.model.albumDetail.Tracks
import com.example.deezerapimusicapp.model.albums.AlbumsModel
import com.example.deezerapimusicapp.util.loadUrl
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

@ExperimentalTime
class AlbumDetailAdapter(
    private val albumDetails: List<DataX>,
    private val duration : List<DataX>,
    private val onClickListener: OnClickListener
) : ListAdapter<DataX, AlbumDetailAdapter.ViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.album_details_recycler_row, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return albumDetails.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(albumDetails[position],duration[position])
        holder.itemView.setOnClickListener {
            onClickListener.clickListener(albumDetails[position])
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = AlbumDetailsRecyclerRowBinding.bind(itemView)
        fun bind(albumList: DataX,duration : DataX){
        albumList.album.cover_medium.let {
            binding.albumDetailsRecyclerViewSongImageView.loadUrl(it)
        }
            albumList.title.let {
                binding.albumDetailsRecyclerViewSongName.text = it
            }
           duration.duration.let {
                val forMin = it.seconds
                binding.albumDetailsRecyclerViewRelease.text = forMin.toString()
            }

        }
    }

    class DiffCallback : DiffUtil.ItemCallback<DataX>() {
        override fun areItemsTheSame(oldItem: DataX, newItem: DataX): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataX, newItem: DataX): Boolean {
            return oldItem == newItem
        }
    }


    class OnClickListener(val clickListener: (albumDetailList: DataX) -> Unit) {
        fun onClick(albumList: DataX) = clickListener(albumList)
    }

}