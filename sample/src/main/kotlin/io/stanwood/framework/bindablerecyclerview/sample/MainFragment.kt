package io.stanwood.framework.bindablerecyclerview.sample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import io.stanwood.framework.bindablerecyclerview.sample.databinding.FragmentMainBinding


class MainFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            FragmentMainBinding.inflate(inflater, container, false).apply {
                vm = MainViewModel()
            }.root
}