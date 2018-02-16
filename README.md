# Bindable RecyclerView

RecyclerView adapter and bindings for **MVVM** architecture. No need for adapters and view holders anymore!

```xml
item_layout="@{@layout/item_sample}"
items="@{vm.items}"
orientation="@{LinearLayoutManager.VERTICAL}"
```

## Usage

* [ViewModel](#viewmodel)
* [xml layout](#xml-layout)

If you're lost, check out the sample app!

### ViewModel

Create your main ViewModel class and add a list of your item's ViewModels.

```kotlin
class MyViewModel {
    val items = ObservableArrayList<MyItemViewModel>() // regular lists work too
}
```

### XML layout

1. Add your main ViewModel as your data variable. For example:
```xml
<variable name="vm" type="com.sample.MyViewModel" />
```

2. Define following properties in your RecyclerView:

* `item_layout` - List item layout
* `items` - List of your items
* `orientation` - Recycler view orientation (vertical or horizontal)

```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>

        <variable name="vm" type="com.sample.MyViewModel" />
        <import type="android.support.v7.widget.LinearLayoutManager" />
    </data>
    ...
    <android.support.v7.widget.RecyclerView
        divider="@{LinearLayoutManager.VERTICAL}"
        item_layout="@{@layout/item_sample}"
        items="@{vm.items}"
        orientation="@{LinearLayoutManager.VERTICAL}"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
    ...
</layout>
```
