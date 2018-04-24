package com.example.a694065.testkotlin.view

import android.app.Application
import android.support.multidex.MultiDexApplication
import com.example.a694065.testkotlin.di.appModule
import com.example.a694065.testkotlin.di.dataModule
import com.example.a694065.testkotlin.di.domainModule
import com.facebook.stetho.Stetho
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import io.realm.Realm
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.LazyKodein
import kotlin.reflect.KProperty

class App: MultiDexApplication(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(appModule(this@App))
        import(dataModule)
        import(domainModule)
    }


    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Stetho.initialize(
                Stetho.newInitializerBuilder(this).
                        enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build())
    }
}
