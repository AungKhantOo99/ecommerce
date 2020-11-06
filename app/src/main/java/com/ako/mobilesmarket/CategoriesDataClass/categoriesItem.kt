package com.ako.mobilesmarket.CategoriesDataClass

data class categoriesItem(
    val __v: Int,
    val _id: String,
    val categoryId: String,
    val image: String,
    val name: String,
    val price: Double,
    val relatedImage: ArrayList<String>,
    val specification: String
)