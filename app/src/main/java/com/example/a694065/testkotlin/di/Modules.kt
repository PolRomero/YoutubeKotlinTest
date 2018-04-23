package com.example.a694065.testkotlin.di

import android.content.Context
import com.example.a694065.testkotlin.executor.Rxecutor
import com.example.data.datasource.database.DatabaseDataSource
import com.example.data.datasource.database.RealmDataSource
import com.example.data.repository.RealmRepository
import com.example.data.repository.YoutubeRepositoryData
import com.example.domain.executor.Executor
import com.example.domain.interactor.usecases.GetHistorySaved
import com.example.domain.interactor.usecases.SearchInYoutube
import com.example.domain.interactor.usecases.UpdateHistoryWithHistory
import com.example.domain.interactor.usecases.UpdateHistoryWithVideo
import com.example.domain.repository.DatabaseRepository
import com.example.domain.repository.YoutubeRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val dataModule = Kodein.Module {

    bind<DatabaseDataSource>() with singleton { RealmDataSource() }
    bind<DatabaseRepository>() with singleton {  RealmRepository( realmDataSource = instance() ) }
    bind<YoutubeRepository>() with singleton { YoutubeRepositoryData() }
}

val domainModule = Kodein.Module {
    bind() from singleton { GetHistorySaved(executor = instance(), databaseRepository = instance()) }
    bind() from singleton { SearchInYoutube(executor =  instance(), youtubeRepository = instance()) }
    bind() from singleton { UpdateHistoryWithHistory(executor = instance(), databaseRepository = instance())}
    bind() from singleton { UpdateHistoryWithVideo(executor = instance(), databaseRepository = instance())}
}

fun appModule (context: Context) = Kodein.Module {
    bind<Context>() with singleton { context }
    bind<Executor>() with singleton { Rxecutor() }
}