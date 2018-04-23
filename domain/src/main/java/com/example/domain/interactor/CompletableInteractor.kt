package com.example.domain.interactor

import com.example.domain.executor.Executor
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver


abstract class CompletableInteractor(private val executor: Executor,
                                     private val compositeDisposable: CompositeDisposable = CompositeDisposable()) {

    fun execute(onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        val completable = buildCompletable()
                .subscribeOn(executor.new())
                .observeOn(executor.main())

        compositeDisposable.add(completable
                .subscribeWith(object: DisposableCompletableObserver() {
                    override fun onComplete() {
                        onSuccess()
                    }

                    override fun onError(e: Throwable) {
                        onError(e)
                    }

                }))
    }

    fun clear() =
            compositeDisposable.clear()

    abstract fun buildCompletable(): Completable
}