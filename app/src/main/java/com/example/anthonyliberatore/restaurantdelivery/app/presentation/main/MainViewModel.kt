package com.example.anthonyliberatore.restaurantdelivery.app.presentation.main

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import com.example.anthonyliberatore.restaurantdelivery.app.ext.addTo
import com.example.anthonyliberatore.restaurantdelivery.app.rx.StickyAction
import com.domain.model.Restaurant
import com.domain.usecase.GetRestaurantListUseCase
import com.domain.usecase.GetRestaurantListUseCase.Result
import com.example.anthonyliberatore.restaurantdelivery.app.presentation.imagedetail.ImageDetailActivity
import com.example.anthonyliberatore.restaurantdelivery.app.presentation.restaurantdetail.RestaurantDetailActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val getRestaurantListUseCase: GetRestaurantListUseCase,
        private val mainRouter: MainRouter
) {

    val disposables = CompositeDisposable()
    val progressVisible = ObservableBoolean()
    val restaurantList = ObservableArrayList<Restaurant>()
    val showErrorGettingRestaurants = StickyAction<Boolean>()

    companion object {
        // Hardcoded LatLng for simplicity here
        const val LAT = 37.422740
        const val LNG = -122.139956
    }

    // Called onCreate. Retrieves the list of restaurants
    fun bound() {
        //offset and limit can be used for pagination in the future. This is static for now.
        getRestaurantListUseCase.execute(LAT, LNG, 0, 50)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { handleGetRestaurantListResult(it) }
                .addTo(disposables)
    }

    // Called onDestroy. Clean up method.
    fun unbound() {
        disposables.clear()
    }

    // Handles Result from getRestaurantListUseCase
    private fun handleGetRestaurantListResult(result: Result) {
        progressVisible.set(result == Result.Loading)
        when (result) {
            is Result.Success -> {
                restaurantList.addAll(result.restaurants)
            }
            is Result.Failure -> {
                showErrorGettingRestaurants.trigger(true)
            }
        }
    }

    // Shows restaurant detail screen based on restaurant clicked
    fun onRestaurantClicked(restaurant: Any) {
        mainRouter.navigate(MainRouter.Route.RESTAURANT_DETAIL, Bundle().apply {
                    putInt(RestaurantDetailActivity.EXTRA_RESTAURANT_ID, (restaurant as Restaurant).id)
                })
    }

    // Shows image detail screen based on restaurant clicked
    fun onRestaurantImageClicked(restaurant: Any) {
        mainRouter.navigate(MainRouter.Route.IMAGE_DETAIL, Bundle().apply {
            putString(ImageDetailActivity.EXTRA_URL, (restaurant as Restaurant).coverImgUrl)
        })
    }
}
