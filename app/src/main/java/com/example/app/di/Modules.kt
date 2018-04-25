package com.example.app.di

import android.content.Context
import com.example.app.executor.Rxecutor
import com.example.data.datasource.database.DatabaseDataSource
import com.example.data.datasource.database.RealmDataSource
import com.example.data.repository.HistoryRepository
import com.example.data.repository.YoutubeRepositoryData
import com.example.domain.executor.Executor
import com.example.domain.interactor.usecases.*
import com.example.domain.repository.DatabaseRepository
import com.example.domain.repository.YoutubeRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val dataModule = Kodein.Module {

    bind<DatabaseDataSource>() with singleton { RealmDataSource() }
    bind<DatabaseRepository>() with singleton {  HistoryRepository( realmDataSource = instance() ) }
    bind<YoutubeRepository>() with singleton { YoutubeRepositoryData() }
}

val domainModule = Kodein.Module {
    bind() from singleton { GetHistorySavedUseCase(executor = instance(), databaseRepository = instance()) }
    bind() from singleton { SearchInYoutubeUseCase(executor =  instance(), youtubeRepository = instance()) }
    bind() from singleton { UpdateHistoryWithHistoryUseCase(executor = instance(), databaseRepository = instance())}
    bind() from singleton { UpdateHistoryWithVideoUseCase(executor = instance(), databaseRepository = instance())}
    bind() from singleton { GetHistoryByTitleUseCase(executor = instance(), databaseRepository = instance())}
}

fun appModule (context: Context) = Kodein.Module {
    bind<Context>() with singleton { context }
    bind<Executor>() with singleton { Rxecutor() }
}