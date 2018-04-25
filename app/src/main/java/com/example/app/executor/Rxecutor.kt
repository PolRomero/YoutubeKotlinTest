package com.example.app.executor

import com.example.domain.executor.Executor
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Rxecutor: Executor {
    override fun main(): Scheduler = AndroidSchedulers.mainThread()
    override fun new(): Scheduler = Schedulers.io()
}