package com.iteach.tezyordam.base

data class Order(
    val complaint: String,
    val phone: String,
    val date: Long,
    val Latitude: Double,
    val longitude: Double,
    val condition: Int,
)