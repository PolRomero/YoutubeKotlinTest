package com.example.data.datasource.database

import android.util.Log
import com.example.data.datasource.database.realm.Rxrealm
import com.example.data.mappers.toModel
import com.example.data.model.HistoryData
import com.example.data.model.VideoData
import com.example.domain.model.History
import io.reactivex.Completable
import io.reactivex.Single
import io.realm.Realm


class RealmDataSource: DatabaseDataSource {

    override fun getHistory(): Single<History> {
        val query = Rxrealm.getElement {
            it.where(HistoryData::class.java).findFirst()
        }
        return query.toSingle().map { it.toModel() }
    }

    override fun getHistoryByTitle(search: String): Single<History> {
        val query = Rxrealm.getList {
            it.where(VideoData::class.java).contains("title", search).findAll()
        }
        val history = HistoryData()
        //return query.toSingle()
        TODO()
    }

    override fun updateHistory(history: HistoryData): Completable {
        Log.d("RealmDataSource", "Updating History with history")
        history.update()
        return Completable.complete()
    }

    override fun updateHistory(video: VideoData): Completable {
        Log.d("RealmDataSource", "Updating History with video")
        video.updateHistory()
        return Completable.complete()
    }
}

fun HistoryData.update() {
    val realm = Realm.getDefaultInstance()
    realm.executeTransaction {
        val realmHistory = it.where(HistoryData::class.java).findFirst()
        if (realmHistory != null) {
            realmHistory.videos = this.videos
        } else {
            val newHistory = realm.createObject(HistoryData::class.java)
            newHistory.videos = this.videos
        }
    }
    realm.close()
}

fun VideoData.updateHistory() {
    val realm = Realm.getDefaultInstance()
    realm.executeTransaction {
        val realmHistory = it.where(HistoryData::class.java).findFirst()
        if (realmHistory != null) {
            if(realmHistory.videos.where().equalTo("id", this.id).findAll().size == 0)
                realmHistory.videos.add(this)
        } else {
            val newHistory = realm.createObject(HistoryData::class.java)
            newHistory.videos.add(this)
        }
    }
    realm.close()
}

