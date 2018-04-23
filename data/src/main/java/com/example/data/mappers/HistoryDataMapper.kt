package com.example.data.mappers

import com.example.data.model.HistoryData
import com.example.data.model.VideoData
import com.example.domain.model.History
import com.example.domain.model.Video

fun HistoryData.toModel() : History {
    val history = History()
    videos.forEach{ history.add(it.toModel()) }
    return history
}


fun VideoData.toModel() : Video =
        Video(title = title,
                description = description,
                channel = channel,
                thumbnail = thumbnailUrl,
                id = id)

fun History.toData() : HistoryData {
    val historyData = HistoryData()
    history.forEach{ historyData.add(it.toData()) }
    return historyData
}

fun Video.toData() : VideoData =
        VideoData(title = title,
                description = description,
                channel = channel,
                thumbnailUrl = thumbnail,
                id = id)
