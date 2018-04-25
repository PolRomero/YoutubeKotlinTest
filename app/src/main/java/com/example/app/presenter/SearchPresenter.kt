package com.example.app.presenter

import android.util.Log
import com.example.app.mapper.toModel
import com.example.app.mapper.toView
import com.example.app.model.HistoryView
import com.example.app.navigation.NavigationManager
import com.example.domain.interactor.usecases.SearchInYoutubeUseCase
import com.example.domain.interactor.usecases.UpdateHistoryWithVideoUseCase


class SearchPresenter(val searchInYoutubeUseCase: SearchInYoutubeUseCase, val updateHistoryUseCase: UpdateHistoryWithVideoUseCase, view: View): Presenter<SearchPresenter.View>(view) {

    var searchList = HistoryView(ArrayList())

    override fun initialize() {

    }

    override fun resume() {
        super.resume()
        navigationManager.eventSubject.onNext(NavigationManager.ScreenEvent.VIDEO_SEARCH)
    }

    override fun stop() {
        super.stop()
    }

    override fun destroy() {
        searchInYoutubeUseCase.clear()
        updateHistoryUseCase.clear()
    }


    fun searchResults(query: String) {
        searchInYoutubeUseCase.execute(query,
                { updateResults(it.toView()) },
                { it.printStackTrace() })
    }

    fun onItemClick(): (position: Int) -> Unit = { updateHistoryUseCase.execute(searchList.history[it].toModel(), {}, {it.printStackTrace()}) }


    fun updateResults(history: HistoryView) {
        searchList = history
        Log.d("Presenter", "Results received = " + searchList.history.size)
        view.updateSearchResults(searchList)
    }

    interface View: Presenter.View {
        fun updateSearchResults(history: HistoryView)
    }
}