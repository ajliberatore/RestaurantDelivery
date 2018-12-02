package com.example.anthonyliberatore.restaurantdelivery.app.rx

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

@Suppress("UNCHECKED_CAST")
class StickyAction<T> {
  private val subject = BehaviorSubject.create<T>()
  private val EMPTY = Any()

  fun trigger(value: T?) {
    if (value == null) {
      subject.onNext(EMPTY as T)
      return
    }
    subject.onNext(value)
  }

  fun observe(): Observable<T> {
    return subject.filter { value -> value != EMPTY }.doOnNext({ this.clearValue(it) })
  }

  fun value(): T {
    return subject.value
  }

  private fun clearValue(value: T) {
    if (value != EMPTY) {
      subject.onNext(EMPTY as T)
    }
  }
}
