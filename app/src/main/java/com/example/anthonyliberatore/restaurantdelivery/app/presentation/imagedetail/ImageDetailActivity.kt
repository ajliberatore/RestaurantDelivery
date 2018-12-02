package com.example.anthonyliberatore.restaurantdelivery.app.presentation.imagedetail

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.anthonyliberatore.restaurantdelivery.R
import com.example.anthonyliberatore.restaurantdelivery.app.presentation.BaseActivity
import com.example.anthonyliberatore.restaurantdelivery.databinding.ActivityImageDetailBinding
import javax.inject.Inject

class ImageDetailActivity: BaseActivity() {

    companion object {
        const val EXTRA_URL = "URL"
    }

    @Inject lateinit var viewModel: ImageDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        screenComponent.inject(this)

        val binding: ActivityImageDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_image_detail)
        binding.viewModel = viewModel

        viewModel.bound(intent.extras.getString(EXTRA_URL, ""))
    }
}
