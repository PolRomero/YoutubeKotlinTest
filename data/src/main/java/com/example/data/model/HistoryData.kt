package com.example.data.model

import io.realm.RealmList
import io.realm.RealmObject
import java.util.*

open class HistoryData(val videos: RealmList<VideoData> = RealmList<VideoData>()) : RealmObject() {

    fun add(video : VideoData) = videos.add(video)

    fun clear() = videos.clear()
}