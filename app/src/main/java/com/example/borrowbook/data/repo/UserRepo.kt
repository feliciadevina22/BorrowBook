package com.example.borrowbook.data.repo

import androidx.lifecycle.LiveData
import com.example.borrowbook.data.model.User
import com.example.borrowbook.data.rest.UserDao

class UserRepo(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}