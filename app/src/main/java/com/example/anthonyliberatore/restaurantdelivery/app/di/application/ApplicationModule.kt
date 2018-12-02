package com.example.anthonyliberatore.restaurantdelivery.app.di.application

import com.example.anthonyliberatore.restaurantdelivery.app.BaseApplication
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: BaseApplication) {

  @Provides
  @Singleton
  fun provideApplication(): BaseApplication {
    return application
  }

  @Provides
  @Singleton
  fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://api.doordash.com/v2/")
        .build()
  }
}
