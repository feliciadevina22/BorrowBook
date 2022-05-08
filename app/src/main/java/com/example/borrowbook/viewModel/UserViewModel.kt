package com.example.borrowbook.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.borrowbook.data.database.BorrowBookDatabase
import com.example.borrowbook.data.model.User
import com.example.borrowbook.data.repo.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val repository : UserRepo

    init {
        val userDao = BorrowBookDatabase.getDatabase(application).userDao()
        repository = UserRepo(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
}