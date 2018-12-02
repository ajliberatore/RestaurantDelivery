package com.example.anthonyliberatore.restaurantdelivery.app.presentation.restaurantdetail

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.example.anthonyliberatore.restaurantdelivery.R.layout
import com.example.anthonyliberatore.restaurantdelivery.R.string
import com.example.anthonyliberatore.restaurantdelivery.app.ext.addTo
import com.example.anthonyliberatore.restaurantdelivery.app.presentation.BaseActivity
import com.example.anthonyliberatore.restaurantdelivery.databinding.ActivityRestaurantDetailBinding
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RestaurantDetailActivity : BaseActivity() {

  @Inject lateinit var viewModel: RestaurantDetailViewModel
  private val disposables = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding: ActivityRestaurantDetailBinding = DataBindingUtil.setContentView(this,
        layout.activity_restaurant_detail)

    screenComponent.inject(this)

    binding.viewModel = viewModel
    viewModel.bound(intent.getIntExtra(EXTRA_RESTAURANT_ID, -1))
  }

  override fun onResume() {
    super.onResume()
    viewModel.showErrorGettingRestaurantDetails.observe()
        .subscribe {
          AlertDialog.Builder(this)
              .setTitle(getString(string.error_title))
              .setMessage(getString(string.restaurant_detail_message))
              .setNeutralButton(getString(string.ok), { dialog, _ -> dialog.dismiss() })
        }.addTo(disposables)
  }

  override fun onPause() {
    disposables.clear()
    super.onPause()
  }

  override fun onDestroy() {
    viewModel.unbound()
    super.onDestroy()
  }

  companion object {
    const val EXTRA_RESTAURANT_ID = "restaurant_id"
  }
}
