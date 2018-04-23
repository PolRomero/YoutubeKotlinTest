package com.example.a694065.testkotlin.mapper

import android.widget.VideoView
import com.example.a694065.testkotlin.model.HistoryView
import com.example.a694065.testkotlin.model.VideoCustView
import com.example.domain.model.History
import com.example.domain.model.Video

fun History.toView(): HistoryView {
    val list = ArrayList<VideoCustView>()
    history.forEach{ list.add(it.toView()) }
    return HistoryView(list)
}

fun Video.toView(): VideoCustView =
        VideoCustView(title = title,
            description = description,
            channel = channel,
            thumbnail = thumbnail,
            id = id)

fun VideoCustView.toModel(): Video =
        Video(title = title,
              description = description,
              channel = channel,
              thumbnail = thumbnail,
              id = id)

fun HistoryView.toModel() : History {
    val historyModel = History()
    history.forEach { historyModel.add(it.toModel()) }
    return historyModel
}