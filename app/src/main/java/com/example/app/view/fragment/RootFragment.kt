package com.example.app.view.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app.presenter.Presenter
import com.example.app.view.activity.RootActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

abstract class RootFragment<out V: Presenter.View>: Fragment(), KodeinAware, Presenter.View {

    abstract val presenter: Presenter<V>
    abstract val resourceId: Int

    abstract val fragmentModule: Kodein.Module

    override val kodein = Kodein.lazy {
        extend((activity as RootActivity<*>).kodein)
        import(fragmentModule)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(resourceId, container)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        presenter.initialize()
    }

    override fun onResume() {
        super.onResume()
        presenter.resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }
}