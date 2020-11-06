package com.ako.mobilesmarket

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ako.mobilesmarket.RoomDataBase.RoomAppDB
import com.ako.mobilesmarket.RoomDataBase.UserEntity
import com.google.firebase.auth.FirebaseAuth

class MainActivityViewModel(app: Application) : AndroidViewModel(app){
    lateinit var allUsers: MutableLiveData<List<UserEntity>>
    init {
        allUsers= MutableLiveData()
    };
    fun getAllUsersObservers(): MutableLiveData<List<UserEntity>> {
        return allUsers
    }
    fun getAllUsers(){
        val userdao= RoomAppDB.getAppdb((getApplication()))?.userDao()
        val List=userdao?.getAllUserInfo(FirebaseAuth.getInstance().currentUser!!.uid.toString())
        allUsers.postValue(List)
    }
    fun insertUserInfo(entity: UserEntity){
        val userdao= RoomAppDB.getAppdb((getApplication()))?.userDao()
        userdao?.insertUser(entity)
        getAllUsers()
    }
    fun updateUserInfo(entity: UserEntity){
        val userdao= RoomAppDB.getAppdb((getApplication()))?.userDao()
        userdao?.update(entity)
        getAllUsers()
    }
    fun deleteUserInfo(entity: UserEntity){
        val userdao= RoomAppDB.getAppdb((getApplication()))?.userDao()
        userdao?.deleteUser(entity)
        getAllUsers()
    }
}