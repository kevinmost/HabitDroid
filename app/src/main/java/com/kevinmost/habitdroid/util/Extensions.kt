package com.kevinmost.habitdroid.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class Extensions {
  fun Activity.hideKeyboard() {
    val manager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    manager?.hideSoftInputFromWindow(currentFocus.windowToken, 0)
  }

  fun Int.dpToPx(context: Context): Float {
    return this * context.resources.displayMetrics.density
  }

  fun View.show() {
    this.visibility = View.VISIBLE
  }

  fun View.invisible() {
    this.visibility = View.INVISIBLE
  }

  fun View.hide() {
    this.visibility = View.GONE
  }

  fun Activity.jump(clazz: Class<out Activity>) {

  }
}