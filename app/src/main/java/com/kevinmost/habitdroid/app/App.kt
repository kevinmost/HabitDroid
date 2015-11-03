package com.kevinmost.habitdroid.app

import android.app.Application
import com.kevinmost.habitdroid.dagger.AppComponent
import com.kevinmost.habitdroid.dagger.AppModule
import com.kevinmost.habitdroid.dagger.DaggerAppComponent

object App : Application() {
  @JvmStatic lateinit public var graph: AppComponent

  override fun onCreate() {
    super.onCreate()
    graph = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    graph.inject(this)
  }
}