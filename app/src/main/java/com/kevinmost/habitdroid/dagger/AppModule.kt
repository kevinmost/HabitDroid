package com.kevinmost.habitdroid.dagger

import android.content.Context
import com.kevinmost.habitdroid.app.App
import com.squareup.otto.Bus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {
  @Provides
  @Singleton
  internal fun application(): App {
    return app;
  }

  @Provides
  @Singleton
  internal fun context(): Context {
    return app;
  }

  @Provides
  @Singleton
  internal fun bus(): Bus {
    return Bus();
  }
}