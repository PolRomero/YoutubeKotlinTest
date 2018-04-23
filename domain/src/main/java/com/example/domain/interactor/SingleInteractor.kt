package com.example.domain.interactor

import com.example.domain.executor.Executor
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.disposables.ArrayCompositeDisposable
import io.reactivex.observers.DisposableSingleObserver


abstract class SingleInteractor<T: Any>(private val executor: Executor,
                                        private val compositeDisposable: CompositeDisposable = CompositeDisposable()) {
    fun execute(onSuccess: (T) -> Unit, onError: (Throwable) -> Unit): Single<T> {
        val single = buildSingle()
                .subscribeOn(executor.new())
                .observeOn(executor.main())

        compositeDisposable.add(single
                .subscribeWith(object: DisposableSingleObserver<T>() {
                    override fun onError(e: Throwable) {
                        onError(e)
                    }

                    override fun onSuccess(t: T) {
                        System.out.println("Single interactor: Succesful connection")
                        onSuccess(t)
                    }
                }))

        return single
    }

    fun clear() {
        compositeDisposable.clear()
    }

    abstract fun buildSingle(): Single<T>
}