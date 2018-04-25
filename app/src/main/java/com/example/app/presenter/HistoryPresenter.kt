package com.example.app.presenter

import android.util.Log
import com.example.app.mapper.toView
import com.example.app.model.HistoryView
import com.example.app.navigation.NavigationManager
import com.example.domain.interactor.usecases.GetHistoryByTitleUseCase
import com.example.domain.interactor.usecases.GetHistorySavedUseCase
import com.example.domain.interactor.usecases.UpdateHistoryWithHistoryUseCase

class HistoryPresenter(val getHistoryViewUseCase: GetHistorySavedUseCase, val updateHistoryWithHistoryUseCase: UpdateHistoryWithHistoryUseCase,
                       val getHistoryByTitleUseCase: GetHistoryByTitleUseCase, view: View): Presenter<HistoryPresenter.View>(view) {

    lateinit var history: HistoryView

    override fun initialize() {
        //getHistoryViewUseCase.execute( { history = it.toView(); view.startAdapter() }, { it.printStackTrace() })
    }

    override fun resume() {
        super.resume()
        navigationManager.eventSubject.onNext(NavigationManager.ScreenEvent.HISTORY)
        getAllHistory()
    }

    override fun stop() {
        super.stop()
    }

    override fun destroy() {
        getHistoryViewUseCase.clear()
        getHistoryByTitleUseCase.clear()
        updateHistoryWithHistoryUseCase.clear()
    }


    fun searchResults(search: String) {
        getHistoryByTitleUseCase.execute(search = search,
                onSuccess = { updateSearchResult(it.toView()) },
                onError = { it.printStackTrace()})
    }

    fun updateSearchResult(history: HistoryView) {
        this.history = history
        view.updateHistory()
    }

    fun getAllHistory() {
        getHistoryViewUseCase.execute(
                onSuccess = { history = it.toView(); view.updateHistory()
                    Log.d("History Size", history.history.size.toString())
                    Log.d("It Size", it.history.size.toString())
                },
                onError = { it.printStackTrace() })
    }

    interface View: Presenter.View {
        fun startAdapter()
        fun updateHistory()
    }
}