package com.ako.mobilesmarket.ApiDataClass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class productItem(
    val __v: Int,
    val _id: String,
    val categoryId: CategoryId,
    val image: String,
    val name: String,
    val price: Double,
    val relatedImage: ArrayList<String>,
    val specification: String
) : Parcelable