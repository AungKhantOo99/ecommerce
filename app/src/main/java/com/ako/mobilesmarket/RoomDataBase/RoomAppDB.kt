package com.ako.mobilesmarket.RoomDataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class],version = 1, exportSchema = false)
abstract class RoomAppDB: RoomDatabase(){

    abstract fun userDao():UserDao?
    companion object{
        private var INSTANCE:RoomAppDB?=null
        fun getAppdb(context: Context):RoomAppDB?{
            if(INSTANCE == null){
                INSTANCE= Room.databaseBuilder<RoomAppDB>(
                    context.applicationContext,RoomAppDB::class.java,"Appdb"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }

}