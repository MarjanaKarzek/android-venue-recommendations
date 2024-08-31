package com.karzek.core.error

import timber.log.Timber

class CrashReportingTree : Timber.Tree() {
  override fun log(
    priority: Int,
    tag: String?,
    message: String,
    t: Throwable?
  ) {
    //do nothing, add reporting here
  }
}