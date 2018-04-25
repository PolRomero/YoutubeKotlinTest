package com.example.data.repository

import com.example.data.datasource.database.DatabaseDataSource
import com.example.data.datasource.database.RealmDataSource
import com.example.data.mappers.toData
import com.example.domain.model.History
import com.example.domain.model.Video
import com.example.domain.repository.DatabaseRepository
import io.reactivex.Completable
import io.reactivex.Single

class HistoryRepository(val realmDataSource: DatabaseDataSource): DatabaseRepository {

    override fun updateHistory(video: Video): Completable =
            realmDataSource.updateHistory(video.toData())

    override fun updateHistory(history: History): Completable =
        realmDataSource.updateHistory(history.toData())


    override fun getHistory(): Single<History> =
            realmDataSource.getHistory()

    override fun getHistory(search: String): Single<History> =
            realmDataSource.getHistoryByTitle(search)
}