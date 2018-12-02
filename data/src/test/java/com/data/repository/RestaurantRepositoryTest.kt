package com.data.repository

import com.data.RestaurantApi
import com.data.mapper.RestaurantMapper
import com.data.response.RestaurantResponse
import com.domain.model.Restaurant
import com.domain.repository.RestaurantRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RestaurantRepositoryTest {

  @Mock lateinit var restaurantApi: RestaurantApi
  @Mock lateinit var restaurantMapper: RestaurantMapper
  private lateinit var sut: RestaurantRepository

  @Before
  fun setUp() {
    sut = RestaurantRepositoryImpl(restaurantApi, restaurantMapper)
  }

  @Test
  fun `getRestaurantList gets the restaurantList`() {
    given(restaurantApi.getRestaurantList(any(), any(), any(), any())).willReturn(Single.just(mock()))
    given(restaurantMapper.map(any<List<RestaurantResponse>>())).willReturn(mock())

    sut.getRestaurantList(1.1, 1.1, 0, 50).test()

    verify(restaurantApi).getRestaurantList(any(), any(), any(), any())
  }

  @Test
  fun `getRestaurantList maps the restaurantList`() {
    val restaurantList = arrayListOf<RestaurantResponse>(mock())
    given(restaurantApi.getRestaurantList(any(), any(), any(), any())).willReturn(Single.just(restaurantList))
    given(restaurantMapper.map(any<List<RestaurantResponse>>())).willReturn(mock())

    sut.getRestaurantList(1.1, 1.1, 0, 50).test()

    verify(restaurantMapper).map(eq(restaurantList))
  }

  @Test
  fun `getRestaurantList returns mapped list`() {
    val restaurantList = arrayListOf<RestaurantResponse>(mock())
    val mappedRestaurantList = arrayListOf<Restaurant>(mock())
    given(restaurantApi.getRestaurantList(any(), any(), any(), any())).willReturn(Single.just(restaurantList))
    given(restaurantMapper.map(any<List<RestaurantResponse>>())).willReturn(mappedRestaurantList)

    sut.getRestaurantList(1.1, 1.1, 0, 50).test().assertValue(mappedRestaurantList)
  }

  @Test
  fun `getRestaurantList returns error on failure`() {
    val error = Throwable()
    given(restaurantApi.getRestaurantList(any(), any(), any(), any())).willReturn(Single.error(error))

    sut.getRestaurantList(1.1, 1.1, 0, 50).test().assertError(error)
  }

  @Test
  fun `getRestaurant gets the restaurant`() {
    given(restaurantApi.getRestaurant(any())).willReturn(Single.just(mock()))
    given(restaurantMapper.map(any<RestaurantResponse>())).willReturn(mock())

    sut.getRestaurant(1).test()

    verify(restaurantApi).getRestaurant(any())
  }

  @Test
  fun `getRestaurant maps the restaurant`() {
    val restaurant = mock<RestaurantResponse>()
    given(restaurantApi.getRestaurant(any())).willReturn(Single.just(restaurant))

    sut.getRestaurant(1).test()

    verify(restaurantMapper).map(eq(restaurant))
  }

  @Test
  fun `getRestaurant returns mapped restaurant`() {
    val mappedRestaurant= mock<Restaurant>()
    val restaurant = mock<RestaurantResponse>()
    given(restaurantApi.getRestaurant(any())).willReturn(Single.just(restaurant))
    given(restaurantMapper.map(any<RestaurantResponse>())).willReturn(mappedRestaurant)

    sut.getRestaurant(1).test().assertValue(mappedRestaurant)
  }

  @Test
  fun `getRestaurant returns error on failure`() {
    val error = Throwable()
    given(restaurantApi.getRestaurant(any())).willReturn(Single.error(error))

    sut.getRestaurant(1).test().assertError(error)
  }
}