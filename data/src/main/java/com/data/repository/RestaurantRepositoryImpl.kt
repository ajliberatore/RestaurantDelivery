package com.data.repository

import com.data.RestaurantApi
import com.data.mapper.RestaurantMapper
import com.domain.model.Restaurant
import com.domain.repository.RestaurantRepository
import io.reactivex.Single

/**
 * RestaurantRepositoryImpl implements RestaurantRepository
 *
 * RestaurantRepository is responsible for retrieving the restaurant info from the api layer
 */
class RestaurantRepositoryImpl(
        private val restaurantApi: RestaurantApi,
        private val restaurantMapper: RestaurantMapper
) : RestaurantRepository {

  /**
   * getRestaurantList
   * @return Single<List<Restaurant>> list of nearby restaurants
   * @param lat Latitude of the user
   * @param lng Longitude of the user
   * @param offset the offset of the list to be retrieved
   * @param limit the number of restaurants to be retrieved
   */
  override fun getRestaurantList(lat: Double, lng: Double, offset: Int, limit: Int): Single<List<Restaurant>> {
    return restaurantApi.getRestaurantList(lat, lng, offset, limit)
        .map { restaurantMapper.map(it) }
  }

  /**
   * getRestaurant
   * @return Single<Restaurant> Restaurant details
   * @param id the id of restaurant to be retrieved
   */
  override fun getRestaurant(id: Int): Single<Restaurant> {
    return restaurantApi.getRestaurant(id)
        .map { restaurantMapper.map(it) }
  }
}