package com.example.anthonyliberatore.restaurantdelivery.app.presentation.restaurantdetail

import android.app.Activity
import com.nhaarman.mockito_kotlin.anyOrNull
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.ref.WeakReference

@RunWith(MockitoJUnitRunner::class)
class RestaurantDetailRouterTest {

    @Mock
    lateinit var activity: Activity

    private lateinit var sut: RestaurantDetailRouter

    @Before
    fun setUp() {
        sut = RestaurantDetailRouter(WeakReference(activity))
    }

    @Test
    fun `navigate shows image detail screen when route is image detail`() {
        sut.navigate(RestaurantDetailRouter.Route.IMAGE_DETAIL)

        verify(activity).startActivity(anyOrNull())
    }
}
