package com.example.anthonyliberatore.restaurantdelivery.app.di.screen

import com.example.anthonyliberatore.restaurantdelivery.app.di.scope.PerScreen
import com.example.anthonyliberatore.restaurantdelivery.app.presentation.imagedetail.ImageDetailActivity
import com.example.anthonyliberatore.restaurantdelivery.app.presentation.main.MainActivity
import com.example.anthonyliberatore.restaurantdelivery.app.presentation.restaurantdetail.RestaurantDetailActivity
import dagger.Subcomponent

@PerScreen
@Subcomponent(modules = [ScreenModule::class])
interface ScreenComponent {

  fun inject(mainActivity: MainActivity)

  fun inject(restaurantDetailActivity: RestaurantDetailActivity)

  fun inject(imageDetailActivity: ImageDetailActivity)
}
