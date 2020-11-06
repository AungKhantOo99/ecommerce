package com.ako.mobilesmarket.RoomDataBase

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM userdb WHERE Userid = :id")
    fun getAllUserInfo(id:String):List<UserEntity>?

    @Insert
    fun insertUser(user : UserEntity?)

    @Delete
    fun deleteUser(user: UserEntity?)

    @Update
    fun update(user: UserEntity?)
}