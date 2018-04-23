package com.example.domain.executor

import io.reactivex.Scheduler

interface Executor {
    fun main() : Scheduler
    fun new() : Scheduler
}