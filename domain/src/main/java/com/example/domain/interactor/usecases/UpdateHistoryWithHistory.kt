package com.example.domain.interactor.usecases

import com.example.domain.executor.Executor
import com.example.domain.interactor.CompletableInteractor
import com.example.domain.model.History
import com.example.domain.repository.DatabaseRepository
import io.reactivex.Completable

class UpdateHistoryWithHistory(executor: Executor, val databaseRepository: DatabaseRepository): CompletableInteractor(executor = executor) {

    lateinit var history: History

    fun execute(history: History, onSuccess: () -> Unit, onError: (Throwable) -> Unit)  {
        this.history = history
        super.execute(onSuccess, onError)
    }

    override fun buildCompletable(): Completable = databaseRepository.updateHistory(history)

}