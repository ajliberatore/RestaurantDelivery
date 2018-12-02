package com.data.mapper

import com.data.response.RestaurantResponse
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class RestaurantMapperTest {

  private lateinit var sut: RestaurantMapper
  private lateinit var response: RestaurantResponse

  @Before
  fun setUp() {
    sut = RestaurantMapper()
    response = RestaurantResponse(
        id = 1,
        name = "name",
        description = "description",
        coverImgUrl = "coverImgUrl",
        status = "status",
        deliveryFee = 0
    )
  }

  @Test
  fun `maps id`() {
    val result = sut.map(response)

    assertThat(result.id, equalTo(response.id))
  }

  @Test
  fun `maps name`() {
    val result = sut.map(response)

    assertThat(result.name, equalTo(response.name))
  }

  @Test
  fun `maps description`() {
    val result = sut.map(response)

    assertThat(result.description, equalTo(response.description))
  }

  @Test
  fun `maps coverImgUrl`() {
    val result = sut.map(response)

    assertThat(result.coverImgUrl, equalTo(response.coverImgUrl))
  }

  @Test
  fun `maps status`() {
    val result = sut.map(response)

    assertThat(result.status, equalTo(response.status))
  }

  @Test
  fun `maps deliveryFee`() {
    val result = sut.map(response)

    assertThat(result.deliveryFee, equalTo(response.deliveryFee))
  }
}
