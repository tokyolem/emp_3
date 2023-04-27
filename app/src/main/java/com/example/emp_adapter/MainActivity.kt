package com.example.emp_adapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emp_adapter.databinding.ActivityMainBinding
import com.example.emp_adapter.model.Good
import com.example.emp_adapter.recycler.GoodsAdapter
import com.example.emp_adapter.recycler.GoodsFingerprint

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GoodsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar!!.hide()

        adapter = GoodsAdapter(getGoods())

        with(binding.recycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MainActivity.adapter
        }

        adapter.submitList(goods)

        binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) adapter.submitList(goods.filter { good -> good.isLike })
            else adapter.submitList(goods)
        }
    }

    private fun getGoods() = listOf(
        GoodsFingerprint { item, isChecked ->
            goods.map {
                item.isLike =
                    isChecked; if (binding.checkbox.isChecked) {
                adapter.submitList(goods.filter { good -> good.isLike })
            } else {
                adapter.submitList(goods)
            }
            }
        }
    )

    companion object {
        private inline fun <T> generateList(size: Int, init: (index: Int) -> T): List<T> =
            MutableList(size, init)

        var goods = generateList(
            100
        ) { i -> Good(id = i.toLong(), text = "Good number $i", isLike = false) }
    }

}