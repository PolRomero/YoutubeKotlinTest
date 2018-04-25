package com.example.domain.interactor.usecases

import com.example.domain.executor.Executor
import com.example.domain.interactor.SingleInteractor
import com.example.domain.model.History
import com.example.domain.repository.DatabaseRepository
import io.reactivex.Single

class GetHistoryByTitleUseCase(executor: Executor, val databaseRepository: DatabaseRepository) : SingleInteractor<History>(executor = executor) {

    lateinit var searchString: String

    fun execute(search: String, onSuccess: (History) -> Unit, onError: (Throwable) -> Unit) {
        searchString = search
        super.execute(onSuccess, onError)
    }
    override fun buildSingle(): Single<History> = databaseRepository.getHistory(searchString)
}