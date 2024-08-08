package com.karzek.core.mapper

interface Mapper<in In, out Out> {
  fun convert(data: In): Out
}