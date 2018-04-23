package com.example.domain.interactor.usecases

import com.example.domain.executor.Executor
import com.example.domain.interactor.SingleInteractor
import com.example.domain.model.History
import com.example.domain.repository.DatabaseRepository
import io.reactivex.Single

class GetHistorySaved(executor: Executor, val databaseRepository: DatabaseRepository) : SingleInteractor<History>(executor = executor) {

    override fun buildSingle(): Single<History> = databaseRepository.getHistory()
}