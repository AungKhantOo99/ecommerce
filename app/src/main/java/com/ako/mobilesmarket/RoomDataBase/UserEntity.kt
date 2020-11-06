package com.ako.mobilesmarket.RoomDataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Userdb")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "ID") val id:Int=0,
    @ColumnInfo(name = "Userid") val uid:String,
    @ColumnInfo(name = "BrandName") val bname:String,
    @ColumnInfo(name = "Price") val price:Double,
    @ColumnInfo(name = "Image") val img:String
)