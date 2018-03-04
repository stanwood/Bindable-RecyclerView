package io.stanwood.framework.bindablerecyclerview

import android.databinding.BindingAdapter
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


object RecyclerViewBindingAdapters {

    interface EndOfListReachedListener {
        fun invoke(itemCount: Int)
    }

    @JvmStatic
    @BindingAdapter(value = ["items"])
    fun setItems(
            recyclerView: RecyclerView,
            items: BindableArrayList<*>) {
        val adapter = recyclerView.adapter
        if (adapter == null) {
            recyclerView.adapter = ViewModelAdapter(items, items.layoutResId, items.bindingVariableId)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["orientation"])
    fun setOrientation(
            recyclerView: RecyclerView,
            orientation: Int) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, orientation, false)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["divider"])
    fun setDivider(
            recyclerView: RecyclerView,
            orientation: Int) {
        recyclerView.apply {
            addItemDecoration(DividerItemDecoration(context, orientation))
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["endOfListReached", "loadMoreThreshold"], requireAll = true)
    fun setEndOfListReachedLister(
            recyclerView: RecyclerView,
            listener: EndOfListReachedListener,
            loadMoreThreshold: Int = 2) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView?.layoutManager

                if (layoutManager is LinearLayoutManager) {
                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                    val itemCount = layoutManager.itemCount

                    if (lastVisibleItemPosition + loadMoreThreshold > itemCount) {
                        listener.invoke(itemCount)
                    }
                }
            }
        })
    }
}