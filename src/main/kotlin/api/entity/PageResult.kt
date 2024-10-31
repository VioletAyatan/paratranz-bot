package com.example.api.entity

data class PageResult<T>(
    val page: Int,
    val pageCount: Int,
    val pageSize: Int,
    val results: List<T>,
    val rowCount: Int
)
