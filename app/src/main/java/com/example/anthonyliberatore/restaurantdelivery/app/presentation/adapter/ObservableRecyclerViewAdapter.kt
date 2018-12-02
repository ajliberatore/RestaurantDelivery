package com.example.anthonyliberatore.restaurantdelivery.app.presentation.adapter

import android.databinding.ObservableList
import android.databinding.ObservableList.OnListChangedCallback
import android.support.v7.widget.RecyclerView

/**
 * ObservableRecyclerViewAdapter is used to allow changes to ObservableList to update the RecyclerView
 */
abstract class ObservableRecyclerViewAdapter<T, Holder: RecyclerView.ViewHolder>(
    private val items: ObservableList<T>) : RecyclerView.Adapter<Holder>() {

  lateinit var onItemClickListener: (item: Any) -> Unit

  init {
    items.addOnListChangedCallback(object: ObservableList.OnListChangedCallback<ObservableList<T>>() {
      override fun onChanged(sender: ObservableList<T>?) {
        notifyDataSetChanged()
      }

      override fun onItemRangeRemoved(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
        notifyItemRangeRemoved(positionStart, itemCount)
      }

      override fun onItemRangeMoved(sender: ObservableList<T>?, fromPosition: Int, toPosition: Int, itemCount: Int) {
        notifyDataSetChanged()
      }

      override fun onItemRangeInserted(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
        notifyItemRangeInserted(positionStart, itemCount)
      }

      override fun onItemRangeChanged(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
        notifyItemRangeChanged(positionStart, itemCount)
      }
    })
  }

  override fun getItemCount(): Int = items.size

  fun getItem(i: Int): T {
    return items[i]
  }

  fun getItems(): ObservableList<T> {
    return items
  }
}
