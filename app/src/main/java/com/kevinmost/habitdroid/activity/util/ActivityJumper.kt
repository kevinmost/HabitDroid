package com.kevinmost.habitdroid.activity.util

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.annotation.AnimRes
import kotlin.reflect.KClass

class ActivityJumper {

  constructor(thisActivity: Activity, newActivityClass: Class<out Activity>) {
    this.thisActivity = thisActivity
    this.newActivityClass = newActivityClass
  }

  // Allow Kotlin Activities to call this method without forcing them to invoke the extension method
  constructor(thisActivity: Activity, newActivityClass: KClass<out Activity>) {
    this.thisActivity = thisActivity
    this.newActivityClass = newActivityClass.java
  }

  private val thisActivity: Activity

  private val newActivityClass: Class<out Activity>

  @AnimRes
  public var enterAnimation: Int = 0
    set

  @AnimRes
  public var exitAnimation: Int = 0
    set

  public var extras: Bundle? = null
    set

  public var makeNewBackstackRoot: Boolean = true
    set

  public fun jump() {
    thisActivity.startActivity(buildIntent());
    if (makeNewBackstackRoot) {
      thisActivity.finish();
    }
    if (enterAnimation != 0 && exitAnimation != 0) {
      thisActivity.overridePendingTransition(enterAnimation, exitAnimation);
    }
  }

  private fun buildIntent(): Intent {
    val intent = Intent(thisActivity, newActivityClass)
    if (makeNewBackstackRoot) {
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    }
    if (extras != null) {
      intent.putExtras(extras)
    }
    return intent
  }
}