package com.domain.usecase

import com.domain.model.Restaurant
import com.domain.repository.RestaurantRepository
import com.domain.usecase.GetRestaurantListUseCase
import com.domain.usecase.GetRestaurantListUseCase.Result.Failure
import com.domain.usecase.GetRestaurantListUseCase.Result.Loading
import com.domain.usecase.GetRestaurantListUseCase.Result.Success
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
class GetRestaurantListUseCaseTest {
  @get:Rule
  var rxJavaTestHooksResetRule = RxJavaTestHooksResetRule()

  @Mock lateinit var restaurantRepository: RestaurantRepository

  private lateinit var sut: GetRestaurantListUseCase

  @Before
  fun setUp() {
    sut = GetRestaurantListUseCase(restaurantRepository)
  }

  @Test
  fun `retrieves the restaurant list`() {
    given(restaurantRepository.getRestaurantList(any(), any(), any(), any())).willReturn(Single.error(Throwable()))

    sut.execute(1.1, 1.1, 0, 50).test()

    verify(restaurantRepository).getRestaurantList(any(), any(), any(), any())
  }

  @Test
  fun `shows loading to start`() {
    given(restaurantRepository.getRestaurantList(any(), any(), any(), any())).willReturn(Single.just(mock()))

    sut.execute(1.1, 1.1, 0, 50).test().assertValueAt(0) { (it == Loading) }
  }

  @Test
  fun `returns the success result when success retrieving the restaurant list`() {
    val restaurantList = arrayListOf<Restaurant>()
    given(restaurantRepository.getRestaurantList(any(), any(), any(), any())).willReturn(Single.just(restaurantList))

    sut.execute(1.1, 1.1, 0, 50).test()
        .assertValueAt(1) { (it as Success).restaurants == restaurantList }
  }

  @Test
  fun `returns the failure result when error retrieving the restaurant list`() {
    val throwable = Throwable()
    given(restaurantRepository.getRestaurantList(any(), any(), any(), any())).willReturn(Single.error(throwable))

    sut.execute(1.1, 1.1, 0, 50).test()
        .assertValueAt(1) { (it as Failure).throwable == throwable }
  }
}