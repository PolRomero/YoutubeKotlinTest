package com.example.domain.repository

import com.example.domain.model.History
import com.example.domain.model.Video
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

interface YoutubeRepository {

    fun getVideoSearch(search: String): Single<History>
}

interface DatabaseRepository {
    fun updateHistory(history: History): Completable
    fun updateHistory(video: Video): Completable
    fun getHistory(): Single<History>
}