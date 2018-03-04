package io.stanwood.framework.bindablerecyclerview

import android.databinding.ObservableArrayList
import android.support.annotation.LayoutRes


class BindableArrayList<T: Any>(
        @LayoutRes val layoutResId: Int,
        val bindingVariableId: Int): ObservableArrayList<T>()