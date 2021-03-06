package com.example.a694065.testkotlin.presenter

import android.util.Log
import com.example.a694065.testkotlin.executor.Rxecutor
import com.example.a694065.testkotlin.mapper.toView
import com.example.a694065.testkotlin.model.HistoryView
import com.example.a694065.testkotlin.view.activity.HistoryActivity
import com.example.data.repository.RealmRepository
import com.example.domain.interactor.usecases.GetHistorySaved
import com.example.domain.interactor.usecases.UpdateHistoryWithHistory
import com.example.domain.interactor.usecases.UpdateHistoryWithVideo

class HistoryPresenter(val getHistoryView: GetHistorySaved, val updateHistoryWithHistory: UpdateHistoryWithHistory, view: HistoryPresenter.View): Presenter<HistoryPresenter.View>(view) {

    lateinit var history: HistoryView

    override fun initialize() {
        //getHistoryView.execute( { history = it.toView(); view.startHistory() }, { it.printStackTrace() })
    }

    override fun resume() {
        getHistoryView.execute( { history = it.toView(); view.startHistory();  Log.d("History Size", history.history.size.toString());
            Log.d("It Size", it.history.size.toString()) }, { it.printStackTrace() })
    }

    override fun stop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun destroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    interface View: Presenter.View {
        fun startHistory()
        fun updateHistory()
    }
}