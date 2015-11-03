package com.kevinmost.habitdroid.activity

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import com.kevinmost.habitdroid.activity.util.Jumper
import com.kevinmost.habitdroid.app.App
import com.squareup.otto.Bus
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

  @Inject lateinit var bus: Bus

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(getContentViewLayoutRes())
    App.graph.inject(this)
    bus.register(this)
    ButterKnife.bind(this)
  }

  override fun onDestroy() {
    ButterKnife.unbind(this)
    bus.unregister(this)
    super.onDestroy()
    // TODO(Kevin): This compiles, but I don't know if it runs. Test tomorrow
    Jumper(this, BaseActivity::class.java) {
      bundle {
        putString("foo", "bar")
      }
      animations {
        enterAnimation { android.R.anim.slide_in_left }
        exitAnimation { android.R.anim.slide_out_right }
      }
      flags { 32 }
      makeNewBackstackRoot { true }
    }.jump()
  }

  @LayoutRes
  abstract fun getContentViewLayoutRes(): Int
}
