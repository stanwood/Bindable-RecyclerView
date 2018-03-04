package io.stanwood.framework.bindablerecyclerview.sample

import io.stanwood.framework.bindablerecyclerview.BindableArrayList


class MainViewModel {

    val items = BindableArrayList<ItemViewModel>(R.layout.item_sample, BR.vm)

    init {
        arrayOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
                .map { ItemViewModel(it) }
                .forEach{ viewModel -> items.add(viewModel) }
    }

}