package com.example.deezerapimusicapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.deezerapimusicapp.R
import com.example.deezerapimusicapp.databinding.ArtistDetailsRecyclerRowBinding
import com.example.deezerapimusicapp.model.albumDetail.AlbumDetailsModel
import com.example.deezerapimusicapp.model.albums.Data
import com.example.deezerapimusicapp.util.loadUrl

class ArtistDetailAdapter(
    private val albumList: List<Data>,
    private val onClickListener: OnClickListener
) : ListAdapter<Data, ArtistDetailAdapter.ViewHolder>(DiffCallback()) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
               .inflate(R.layout.artist_details_recycler_row, parent, false)

            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return albumList.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(albumList[position])
            holder.itemView.setOnClickListener {
                onClickListener.clickListener(albumList[position])
            }
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val binding = ArtistDetailsRecyclerRowBinding.bind(itemView)
            fun bind(datas: Data){
                datas.album.title?.let {
                    binding.artistDetailRecyclerViewAlbumName.text = it
                }
                datas.album.cover_medium?.let {
                    binding.artistDetailRecyclerViewAlbumImageView.loadUrl(it)
                }


            }
        }

        class DiffCallback : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }
        }


        class OnClickListener(val clickListener: (albumList: Data) -> Unit) {
            fun onClick(dataList: Data) = clickListener(dataList)
        }
}