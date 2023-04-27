package com.example.emp_adapter.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.example.emp_adapter.adapter.BaseViewHolder
import com.example.emp_adapter.adapter.DiffUtils
import com.example.emp_adapter.adapter.Fingerprint
import com.example.emp_adapter.adapter.Item
import com.example.emp_adapter.model.Good

class GoodsAdapter(
    private val fingerprints: List<Fingerprint<*, *>>
) : ListAdapter<Item, BaseViewHolder<ViewBinding, Item>>(
    DiffUtils(fingerprints)
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ViewBinding, Item> {
        val inflater = LayoutInflater.from(parent.context)
        return fingerprints.find { it.getLayoutId() == viewType }
            ?.getViewHolder(inflater,parent)
            ?.let { it as BaseViewHolder<ViewBinding, Item> }
            ?: throw java.lang.IllegalArgumentException("View type not found: $viewType")
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding, Item>, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        val item = currentList[position]
        return fingerprints.find { it.isRelativeItem(item) }
            ?.getLayoutId()
            ?: throw java.lang.IllegalArgumentException("View type not found: $item")
    }

    override fun getItem(position: Int): Item {
        return currentList[position]
    }

    override fun getItemId(position: Int) =
        (currentList[position] as Good).id

}