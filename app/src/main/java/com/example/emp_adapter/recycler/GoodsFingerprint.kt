package com.example.emp_adapter.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.emp_adapter.R
import com.example.emp_adapter.adapter.BaseViewHolder
import com.example.emp_adapter.adapter.Fingerprint
import com.example.emp_adapter.adapter.Item
import com.example.emp_adapter.databinding.CardBinding
import com.example.emp_adapter.model.Good

class GoodsFingerprint(
    private val checkboxAction: (item: Good, isChecked: Boolean) -> Unit
): Fingerprint<CardBinding, Good> {

    override fun isRelativeItem(item: Item) = item is Good

    override fun getLayoutId() = R.layout.card

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<CardBinding, Good> {
        val binding = CardBinding.inflate(layoutInflater, parent, false)
        return AccountsViewHolder(binding, checkboxAction)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<Good>() {

        override fun areContentsTheSame(oldItem: Good, newItem: Good) =
            oldItem.id == newItem.id

        override fun areItemsTheSame(oldItem: Good, newItem: Good) =
            oldItem == newItem
    }
}

class AccountsViewHolder(
    binding: CardBinding,
    private val checkboxAction: (item: Good, isChecked: Boolean) -> Unit
) : BaseViewHolder<CardBinding, Good>(binding) {

    override fun onBind(item: Good) {
        setAccountsContent(item)
        onCheckboxClick(item)
    }

    private fun setAccountsContent(item: Good) {
        binding.id.text = "${item.id}."
        binding.text.text = item.text
        binding.checkbox.isChecked = item.isLike
    }

    private fun onCheckboxClick(item: Good) {
        binding.checkbox.setOnCheckedChangeListener {
            _, isChecked -> checkboxAction.invoke(item, isChecked)
        }
    }

}
