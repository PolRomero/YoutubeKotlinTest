package com.example.domain.interactor.usecases

import com.example.domain.executor.Executor
import com.example.domain.interactor.SingleInteractor
import com.example.domain.model.History
import com.example.domain.repository.YoutubeRepository
import io.reactivex.Single


class SearchInYoutubeUseCase(val executor: Executor, val youtubeRepository: YoutubeRepository) : SingleInteractor<History>(executor = executor) {

    private lateinit var search: String

    fun execute(search: String, onSuccess: (History) -> Unit, onError: (Throwable) -> Unit) {
        this.search = search
        super.execute(onSuccess, onError)
    }

    override fun buildSingle(): Single<History> = youtubeRepository.getVideoSearch(search)

}