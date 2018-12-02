package com.domain.usecase

import com.domain.model.Restaurant
import com.domain.repository.RestaurantRepository
import com.domain.usecase.GetRestaurantListUseCase.Result.Failure
import com.domain.usecase.GetRestaurantListUseCase.Result.Loading
import com.domain.usecase.GetRestaurantListUseCase.Result.Success
import io.reactivex.Observable
import javax.inject.Inject

/**
 * GetRestaurantListUseCase
 *
 * Used to retrieve the list of restaurants and return the values in a Result object
 * Additional business logic related to the list can be performed here
 * @param restaurantRepository repository to get the list from
 */
class GetRestaurantListUseCase @Inject constructor(private val restaurantRepository: RestaurantRepository) {

  sealed class Result {
    object Loading : Result()
    data class Success(val restaurants: List<Restaurant>) : Result()
    data class Failure(val throwable: Throwable) : Result()
  }

  fun execute(lat: Double, lng: Double, offset: Int, limit: Int): Observable<Result> {
    return restaurantRepository.getRestaurantList(lat, lng, offset, limit)
        .toObservable()
        .map { Success(it) as Result }
        .onErrorReturn { Failure(it) }
        .startWith(Loading)
  }
}
