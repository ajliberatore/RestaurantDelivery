package com.example.anthonyliberatore.restaurantdelivery.app.presentation.adapter

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.anthonyliberatore.restaurantdelivery.app.presentation.adapter.RestaurantListAdapter.Holder
import com.example.anthonyliberatore.restaurantdelivery.databinding.ListItemRestaurantBinding
import com.domain.model.Restaurant
import com.squareup.picasso.Picasso

/**
 * RestaurantListAdapter is used to display data for each restaurant in the list
 */
class RestaurantListAdapter(restaurants: ObservableList<Restaurant>) :
    ObservableRecyclerViewAdapter<Restaurant, Holder>(restaurants) {

  lateinit var onImageClickedListener: (restaurant: Restaurant) -> Unit

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
    return Holder(
        ListItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onItemClickListener, onImageClickedListener)
  }

  override fun onBindViewHolder(holder: Holder, position: Int) {
    holder.bind(getItem(position))
  }

  class Holder(
      private val binding: ListItemRestaurantBinding,
      private val onItemClickListener: ((item: Any) -> Unit)?,
      private val onImageClickedListener: ((restaurant: Restaurant) -> Unit)?) :
      RecyclerView.ViewHolder(binding.root) {

    private lateinit var restaurant: Restaurant

    fun bind(restaurant: Restaurant) {
      this.restaurant = restaurant

      Picasso.get().load(restaurant.coverImgUrl).resize(100, 50).into(binding.image)
      binding.name.text = restaurant.name
      binding.description.text = restaurant.description
      binding.minutes.text = restaurant.status

      binding.root.setOnClickListener { onItemClickListener?.invoke(restaurant) }
      binding.image.setOnClickListener { onImageClickedListener?.invoke(restaurant) }
    }
  }
}
