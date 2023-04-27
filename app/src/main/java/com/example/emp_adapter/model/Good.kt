package com.example.emp_adapter.model

import com.example.emp_adapter.adapter.Item

data class Good(
    val id: Long,
    val text: String,
    var isLike: Boolean,
) : Item
