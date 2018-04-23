package com.example.a694065.testkotlin.presenter

import android.content.ComponentCallbacks2
import android.util.Log
import com.example.a694065.testkotlin.executor.Rxecutor
import com.example.a694065.testkotlin.mapper.toModel
import com.example.a694065.testkotlin.mapper.toView
import com.example.a694065.testkotlin.model.HistoryView
import com.example.data.repository.RealmRepository
import com.example.data.repository.YoutubeRepositoryData
import com.example.domain.interactor.usecases.SearchInYoutube
import com.example.domain.interactor.usecases.UpdateHistoryWithVideo
import com.example.domain.model.History
import com.squareup.picasso.Picasso
import com.squareup.picasso.clear

class SearchPresenter(val searchInYoutube: SearchInYoutube, val updateHistory: UpdateHistoryWithVideo, view: SearchPresenter.View): Presenter<SearchPresenter.View>(view) {

    var searchList = HistoryView(ArrayList())

    override fun initialize() {

    }

    override fun resume() {

    }

    override fun stop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun destroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTrimMemory(level: Int) {
        when(level) {
            ComponentCallbacks2.TRIM_MEMORY_COMPLETE,
            ComponentCallbacks2.TRIM_MEMORY_MODERATE,
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL,
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW,
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE -> Picasso.get().clear()
        }
    }

    fun searchResults(query: String) {
        searchInYoutube.execute(query,
                { updateResults(it) },
                { it.printStackTrace() })
    }

    fun onItemClick(): (position: Int) -> Unit = { updateHistory.execute(searchList.history[it].toModel(), {}, {it.printStackTrace()}) }


    fun updateResults(history: History) {
        searchList = history.toView()
        Log.d("Presenter", "Results received = " + searchList.history.size)
        view.updateSearchResults(searchList)
    }

    interface View: Presenter.View {
        fun updateSearchResults(history: HistoryView)
    }
}