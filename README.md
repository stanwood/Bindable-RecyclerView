# Bindable RecyclerView

RecyclerView adapter and bindings for **MVVM** architecture. No need for adapters and view holders anymore!

## Usage

There are **four** main components to make Bindable recyclerView work: 
* [Item's ViewModel](#items-viewmodel)
* [Main ViewModel](#main-viewmodel)
* [Item's layout](#items-layout)
* [Main xml layout](#main-xml-layout)

If you're lost, check out the sample app!

### Item's ViewModel

Create your ViewModel class. Nothing fancy, just a regular POKO - (Plain Old Kotlin Object) is enough.

```kotlin
class MyItemViewModel {
  val foo: String
    get() = ...
    
  val bar: Int
    get() = ...
}
```

### Main ViewModel

Create your main ViewModel class and add a list of your item's ViewModels.

```kotlin
class MyViewModel {
    val items = ObservableArrayList<MyItemViewModel>() // regular lists work too
}
```

### Item xml layout

1. Create an xml layout corresponding to your ViewModel.
2. Add your previously created item's ViewModel as your **only** data variable. For example:

```xml
<variable name="vm" type="com.sample.MyItemViewModel" />
```

Sample layout:

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>
        <variable name="vm" type="io.stanwood.framework.bindablerecyclerview.sample.ItemViewModel" />
    </data>
    ...
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center|start"
        android:textSize="16sp"
        android:text="@{vm.foo }" />
        
    <ProgressBar
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:progress="@{vm.bar}"/>

</layout>
```

### Main xml layout

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
