package com.example.domain.interactor.usecases

import com.example.domain.executor.Executor
import com.example.domain.interactor.CompletableInteractor
import com.example.domain.model.History
import com.example.domain.model.Video
import com.example.domain.repository.DatabaseRepository
import io.reactivex.Completable

class UpdateHistoryWithVideo(executor: Executor, val databaseRepository: DatabaseRepository): CompletableInteractor(executor = executor) {

    lateinit var video: Video

    fun execute(video: Video, onSuccess: () -> Unit, onError: (Throwable) -> Unit)  {
        System.out.println("UpdateWithVideo: Execute")
        this.video = video
        super.execute(onSuccess, onError)
    }

    override fun buildCompletable(): Completable = databaseRepository.updateHistory(video)

}