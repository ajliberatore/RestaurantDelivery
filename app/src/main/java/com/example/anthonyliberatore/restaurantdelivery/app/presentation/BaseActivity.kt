package com.example.anthonyliberatore.restaurantdelivery.app.presentation

import android.support.v7.app.AppCompatActivity
import com.example.anthonyliberatore.restaurantdelivery.app.BaseApplication
import com.example.anthonyliberatore.restaurantdelivery.app.di.application.ApplicationComponent
import com.example.anthonyliberatore.restaurantdelivery.app.di.screen.ScreenModule

open class BaseActivity : AppCompatActivity() {

  val screenComponent by lazy {
    getApplicationComponent().plus(ScreenModule(this))
  }

  private fun getApplicationComponent(): ApplicationComponent {
    return (application as BaseApplication).component
  }
}