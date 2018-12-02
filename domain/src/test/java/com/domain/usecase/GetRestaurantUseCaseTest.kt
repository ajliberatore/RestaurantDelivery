package com.domain.usecase

import com.domain.model.Restaurant
import com.domain.repository.RestaurantRepository
import com.domain.usecase.GetRestaurantUseCase
import com.domain.usecase.GetRestaurantUseCase.Result.Failure
import com.domain.usecase.GetRestaurantUseCase.Result.Loading
import com.domain.usecase.GetRestaurantUseCase.Result.Success
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.domain.rx.RxJavaTestHooksResetRule

@RunWith(MockitoJUnitRunner::class)
class GetRestaurantUseCaseTest {
  @get:Rule
  var rxJavaTestHooksResetRule = RxJavaTestHooksResetRule()

  @Mock lateinit var restaurantRepository: RestaurantRepository

  private lateinit var sut: GetRestaurantUseCase

  @Before
  fun setUp() {
    sut = GetRestaurantUseCase(restaurantRepository)
  }

  @Test
  fun `retrieves the restaurant`() {
    given(restaurantRepository.getRestaurant(any())).willReturn(Single.error(Throwable()))

    sut.execute(1).test()

    verify(restaurantRepository).getRestaurant(any())
  }

  @Test
  fun `shows loading to start`() {
    given(restaurantRepository.getRestaurant(any())).willReturn(Single.just(mock()))

    sut.execute(1).test().assertValueAt(0) { (it == Loading) }
  }

  @Test
  fun `returns the success result when success retrieving the restaurant`() {
    val restaurant = mock<Restaurant>()
    given(restaurantRepository.getRestaurant(any())).willReturn(Single.just(restaurant))

    sut.execute(1).test()
        .assertValueAt(1) { (it as Success).restaurant == restaurant }
  }

  @Test
  fun `returns the failure result when error retrieving the restaurant`() {
    val throwable = Throwable()
    given(restaurantRepository.getRestaurant(any())).willReturn(Single.error(throwable))

    sut.execute(1).test()
        .assertValueAt(1) { (it as Failure).throwable == throwable }
  }
}