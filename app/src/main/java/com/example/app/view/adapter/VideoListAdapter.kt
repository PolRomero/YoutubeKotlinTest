package com.example.app.view.adapter

import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a694065.testkotlin.R
import com.example.app.model.HistoryView
import com.example.app.model.VideoCustView
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.video_object.view.*


class VideoListAdapter(var history: HistoryView = HistoryView(), var onItemClick: (Int) -> Unit = {}): RecyclerView.Adapter<VideoListAdapter.VideoViewHolder>(), Parcelable {

    constructor(parcel: Parcel): this(parcel.readParcelable(HistoryView::class.java.classLoader) as HistoryView)

    init {
        Picasso.get().setIndicatorsEnabled(true)
    }

    fun modifyItemClick(onItemClick: (Int) -> Unit = {}) {
        this.onItemClick = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.video_object, parent, false)
        return VideoViewHolder(view,
                onItemClick = { onItemClick(it) })
    }

    override fun getItemCount(): Int = history.history.size

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder?.bind(history.history[position])
    }

    fun updateItems(history: HistoryView) {
        this.history = history
        notifyDataSetChanged()
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeParcelable(history, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR: Parcelable.Creator<VideoListAdapter>{
        override fun createFromParcel(source: Parcel): VideoListAdapter {
            return VideoListAdapter(source)
        }

        override fun newArray(size: Int): Array<VideoListAdapter> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
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