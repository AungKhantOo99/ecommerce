package com.ako.mobilesmarket.Related

data class Relatedproduct(
    val _id: String,
    val categoryId: CategoryId,
    val image: String,
    val name: String,
    val price: Double,
    val relatedImage: List<String>,
    val specification: String
)