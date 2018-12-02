package com.example.anthonyliberatore.restaurantdelivery.app.presentation.imagedetail

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Test


import org.junit.Assert.*
import org.junit.Before

class ImageDetailViewModelTest {

    private lateinit var sut: ImageDetailViewModel

    @Before
    fun setUp() {
        sut = ImageDetailViewModel()
    }

    @Test
    fun `bound sets imageUrl to url`() {
        val url = "url"

        sut.bound(url)

        assertThat(sut.imageUrl.get(), equalTo(url))
    }
}
