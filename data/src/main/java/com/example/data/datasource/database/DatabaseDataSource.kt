package com.example.data.datasource.database

import com.example.data.model.HistoryData
import com.example.data.model.VideoData
import com.example.domain.model.History
import io.reactivex.Completable
import io.reactivex.Single

interface DatabaseDataSource {
    fun getHistory(): Single<History>
    fun updateHistory(history: HistoryData): Completable
    fun updateHistory(video: VideoData): Completable
}