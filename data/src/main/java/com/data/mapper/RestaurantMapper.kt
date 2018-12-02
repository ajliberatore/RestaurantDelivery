package com.data.mapper

import com.data.response.RestaurantResponse
import com.domain.model.Restaurant
import javax.inject.Inject

/**
 * RestaurantMapper maps the restaurant response objects to the restaurant model object
 */
class RestaurantMapper @Inject constructor() {

  fun map(responseList: List<RestaurantResponse>): List<Restaurant> {
    return responseList.map { (map(it)) }
  }

  fun map(response: RestaurantResponse): Restaurant {
    return Restaurant(
            id = response.id,
            name = response.name,
            description = response.description,
            coverImgUrl = response.coverImgUrl,
            status = response.status,
            deliveryFee = response.deliveryFee
    )
  }
}
