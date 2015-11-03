package com.kevinmost.habitdroid.dagger

import android.app.Activity
import android.content.Context
import com.kevinmost.habitdroid.app.App
import com.squareup.otto.Bus
import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
  fun inject(app: App)
  fun inject(activity: Activity)
}
