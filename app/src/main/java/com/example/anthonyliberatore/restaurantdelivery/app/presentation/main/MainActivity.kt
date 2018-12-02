package com.example.anthonyliberatore.restaurantdelivery.app.presentation.main

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.anthonyliberatore.restaurantdelivery.R.layout
import com.example.anthonyliberatore.restaurantdelivery.R.string
import com.example.anthonyliberatore.restaurantdelivery.app.ext.addTo
import com.example.anthonyliberatore.restaurantdelivery.app.presentation.BaseActivity
import com.example.anthonyliberatore.restaurantdelivery.app.presentation.adapter.RestaurantListAdapter
import com.example.anthonyliberatore.restaurantdelivery.databinding.ActivityMainBinding
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivity : BaseActivity() {

  @Inject lateinit var viewModel: MainViewModel
  private val disposables = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, layout.activity_main)

    screenComponent.inject(this)

    binding.viewModel = viewModel
    viewModel.bound()
  }

  // onResume we need to subscribe to our viewModel actions
  override fun onResume() {
    super.onResume()
    viewModel.showErrorGettingRestaurants.observe()
        .subscribe {
          AlertDialog.Builder(this)
              .setTitle(getString(string.error_title))
              .setMessage(getString(string.restaurant_list_error_message))
              .setNeutralButton(getString(string.ok)) { dialog, _ -> dialog.dismiss() }
        }.addTo(disposables)
  }

  // onPause we need to unsubscribe from viewModel actions since the view is not backgrounded
  override fun onPause() {
    disposables.clear()
    super.onPause()
  }

  override fun onDestroy() {
    viewModel.unbound()
    super.onDestroy()
  }

  companion object {
    /**
     * bindList uses Databinding to initialize the recyclerView using an ObservableList from the MainViewModel
     * this is referenced in activity_main.xml as 'app:adapter={@viewModel}'
     */
    @JvmStatic
    @BindingAdapter("adapter")
    fun bindList(recyclerView: RecyclerView, viewModel: MainViewModel) {
      val adapter = RestaurantListAdapter(viewModel.restaurantList)
      adapter.onItemClickListener = { viewModel.onRestaurantClicked(it) }
      adapter.onImageClickedListener = { viewModel.onRestaurantImageClicked(it) }
      recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
      recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
      recyclerView.adapter = adapter
    }
  }
}
