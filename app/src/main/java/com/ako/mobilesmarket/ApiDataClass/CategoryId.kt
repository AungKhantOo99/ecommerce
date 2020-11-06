package com.ako.mobilesmarket.ApiDataClass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryId(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val name: String,
    val updatedAt: String
) : Parcelable