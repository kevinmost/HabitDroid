package com.kevinmost.habitdroid.activity.util

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.annotation.AnimRes

fun Jumper(from: Activity, to: Class<out Activity>, init: Jumper.() -> Unit): Jumper {
  val jumper = Jumper()
  jumper.from = from
  jumper.to = to
  jumper.init()
  return jumper
}

class Jumper {
  internal lateinit var from: Activity
  internal lateinit var to: Class<out Activity>
  internal var flags = 0
  internal var makeNewBackstackRoot = true
  internal var animations = Animations()
  internal var bundle = Bundle()

  fun makeNewBackstackRoot(init: () -> Boolean): Boolean {
    makeNewBackstackRoot = init.invoke()
    return makeNewBackstackRoot
  }

  fun flags(init: () -> Int): Int {
    flags = init.invoke()
    return flags
  }

  fun animations(init: Animations.() -> Unit): Animations {
    animations.init()
    return animations
  }

  fun bundle(init: Bundle.() -> Unit): Bundle {
    bundle.init()
    return bundle
  }

  fun jump() {
    from.startActivity(buildIntent())
    if (makeNewBackstackRoot) {
      from.finish()
    }
    if (animations.enterAnimation != 0 || animations.exitAnimation != 0) {
      from.overridePendingTransition(animations.enterAnimation, animations.exitAnimation)
    }
  }

  private fun buildIntent(): Intent {
    val intent = Intent(from, to).putExtras(bundle)
    if (makeNewBackstackRoot) {
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    }
    return intent
  }
}

class Animations {
  @AnimRes
  internal var enterAnimation = 0

  @AnimRes
  internal var exitAnimation = 0

  fun enterAnimation(enterAnimation: () -> @AnimRes Int) {
    this.enterAnimation = enterAnimation.invoke()
  }

  fun exitAnimation(exitAnimation: () -> @AnimRes Int) {
    this.exitAnimation = exitAnimation.invoke()
  }
}
