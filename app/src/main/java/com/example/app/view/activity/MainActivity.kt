package com.example.app.view.activity

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import com.example.a694065.testkotlin.R
import com.example.app.presenter.Presenter
import kotlinx.android.synthetic.main.split_layout_horizontal.view.*
import kotlinx.android.synthetic.main.split_layout_vertical.view.*

class MainActivity: RootActivity<Presenter.View>() {

    override val resourceId = R.layout.split_layout_vertical
}