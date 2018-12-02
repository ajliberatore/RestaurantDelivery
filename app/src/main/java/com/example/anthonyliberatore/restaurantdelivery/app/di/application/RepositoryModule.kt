package com.example.anthonyliberatore.restaurantdelivery.app.di.application

import com.data.RestaurantApi
import com.data.mapper.RestaurantMapper
import com.data.repository.RestaurantRepositoryImpl
import com.domain.repository.RestaurantRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

  @Provides
  @Singleton
  fun provideRestaurantRepository(restaurantApi: RestaurantApi, restaurantMapper: RestaurantMapper): RestaurantRepository {
    return RestaurantRepositoryImpl(restaurantApi, restaurantMapper)
  }

}
