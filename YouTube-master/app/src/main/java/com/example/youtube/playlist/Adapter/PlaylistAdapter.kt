package com.example.youtube.playlist.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.databinding.ItemPlaylistBinding
import com.example.youtube.model.Item
import com.example.youtube.utils.loadImage


class PlaylistAdapter(private val onClick: (Item) -> Unit) :
    RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    private val data: ArrayList<Item> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun addData(newData: List<Item>?) {
        data.clear()
        if (newData != null) {
            data.addAll(newData)
        }
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder(
            ItemPlaylistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bind(data[position])

    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class PlaylistViewHolder(private val binding: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Item) {

            itemView.setOnClickListener {
                onClick(item)
            }

            val data = item.snippet?.channelTitle
            val nameOfPlaylist = item.snippet?.title
            val count = item.contentDetails?.itemCount
            binding.tvVideoCount.text = "$count video Series"
            binding.tvName.text = data.toString()
            binding.data.text = nameOfPlaylist.toString()
            binding.image.loadImage(item.snippet?.thumbnails?.default?.url.toString())


        }


    }
}