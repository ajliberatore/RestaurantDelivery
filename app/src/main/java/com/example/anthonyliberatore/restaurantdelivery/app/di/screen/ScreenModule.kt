package com.example.anthonyliberatore.restaurantdelivery.app.di.screen

import com.example.anthonyliberatore.restaurantdelivery.app.di.scope.PerScreen
import com.example.anthonyliberatore.restaurantdelivery.app.presentation.BaseActivity
import com.example.anthonyliberatore.restaurantdelivery.app.presentation.main.MainRouter
import com.example.anthonyliberatore.restaurantdelivery.app.presentation.restaurantdetail.RestaurantDetailRouter
import dagger.Module
import dagger.Provides
import java.lang.ref.WeakReference

@Module
class ScreenModule(private val activity: BaseActivity) {

  @PerScreen
  @Provides
  fun providesActivity(): BaseActivity {
    return activity
  }

  @PerScreen
  @Provides
  fun providesMainRouter(): MainRouter {
    return MainRouter(WeakReference(activity))
  }

  @PerScreen
  @Provides
  fun providesRestaurantDetailRouter(): RestaurantDetailRouter {
    return RestaurantDetailRouter(WeakReference(activity))
  }
}
