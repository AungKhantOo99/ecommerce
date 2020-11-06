package com.ako.mobilesmarket.Related

data class Product(
    val id: String,
    val image: String,
    val name: String,
    val price: Double,
    val relatedImage: List<String>,
    val specification: String,
    val type: String
)