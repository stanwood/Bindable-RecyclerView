package io.stanwood.framework.bindablerecyclerview.sample


class MainViewModel {

    val items: List<ItemViewModel>
        get() = arrayOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
                .map { ItemViewModel(it) }

}