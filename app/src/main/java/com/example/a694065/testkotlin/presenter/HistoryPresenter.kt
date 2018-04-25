package com.example.a694065.testkotlin.presenter

import android.content.ComponentCallbacks2
import android.util.Log
import com.example.a694065.testkotlin.mapper.toView
import com.example.a694065.testkotlin.model.HistoryView
import com.example.a694065.testkotlin.navigation.NavigationManager
import com.example.domain.interactor.usecases.GetHistoryByTitleUseCase
import com.example.domain.interactor.usecases.GetHistorySavedUseCase
import com.example.domain.interactor.usecases.UpdateHistoryWithHistoryUseCase
import com.squareup.picasso.Picasso
import com.squareup.picasso.clear

class HistoryPresenter(val getHistoryViewUseCase: GetHistorySavedUseCase, val updateHistoryWithHistoryUseCase: UpdateHistoryWithHistoryUseCase,
                       val getHistoryByTitleUseCase: GetHistoryByTitleUseCase, view: HistoryPresenter.View): Presenter<HistoryPresenter.View>(view) {

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