package com.example.a694065.testkotlin.view.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a694065.testkotlin.R
import com.example.a694065.testkotlin.model.HistoryView
import com.example.a694065.testkotlin.model.VideoCustView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.squareup.picasso.clear
import kotlinx.android.synthetic.main.video_object.view.*


class VideoListAdapter(var history: HistoryView, val onItemClick: (Int) -> Unit = {}): RecyclerView.Adapter<VideoListAdapter.VideoViewHolder>() {

    init {
        Picasso.get().setIndicatorsEnabled(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.video_object, parent, false)
        return VideoViewHolder(view,
                onItemClick = {  onItemClick(it) })
    }

    override fun getItemCount(): Int = history.history.size

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder?.bind(history.history[position])
    }

    fun updateItems(history: HistoryView) {
        this.history = history
        notifyDataSetChanged()
    }


    class VideoViewHolder(itemView: View, onItemClick: (Int) -> Unit): RecyclerView.ViewHolder(itemView){

        lateinit var thumbnailUrl: String
        init {
            itemView.setOnClickListener { onItemClick(adapterPosition) }
        }

        fun bind(video: VideoCustView) {
            thumbnailUrl = video.thumbnail
            Picasso.get().load(video.thumbnail).networkPolicy(NetworkPolicy.NO_STORE).into(itemView.image)
            itemView.title.text = video.title
            itemView.description.text = ""
        }

    }
}