/*
 * Copyright 2018 stanwood Gmbh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.stanwood.framework.bindablerecyclerview

import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.databinding.ObservableList


class ViewModelAdapter(
        private val items: List<Any>,
        @LayoutRes private val layoutResId: Int,
        private val variableId: Int):
        RecyclerView.Adapter<ViewModelAdapter.ViewHolder>() {

    private inner class ListChangedCallback : ObservableList.OnListChangedCallback<ObservableList<Any>>() {
        override fun onChanged(objects: ObservableList<Any>) {
            notifyDataSetChanged()
        }

        override fun onItemRangeChanged(objects: ObservableList<Any>, positionStart: Int, itemCount: Int) {
            notifyItemRangeChanged(positionStart, itemCount)
        }

        override fun onItemRangeInserted(objects: ObservableList<Any>, positionStart: Int, itemCount: Int) {
            notifyItemRangeInserted(positionStart, itemCount)
        }

        override fun onItemRangeMoved(objects: ObservableList<Any>, fromPosition: Int, toPosition: Int, itemCount: Int) {
            notifyDataSetChanged()
        }

        override fun onItemRangeRemoved(objects: ObservableList<Any>, positionStart: Int, itemCount: Int) {
            notifyItemRangeRemoved(positionStart, itemCount)
        }
    }

    inner class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.getRoot()) {

        fun bind(item: Any) {
            binding.setVariable(variableId, item)
            binding.notifyPropertyChanged(0)
            binding.executePendingBindings()
            binding.invalidateAll()
        }
    }

    init {
        if(items is ObservableList<Any>) {
            items.addOnListChangedCallback(ListChangedCallback())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val context = parent?.context
        val inflater = LayoutInflater.from(context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutResId, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = items[position]
        holder?.bind(item)
    }
}