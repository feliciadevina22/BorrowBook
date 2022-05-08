package com.example.borrowbook.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.borrowbook.data.database.BorrowBookDatabase
import com.example.borrowbook.data.model.BookLocal
import com.example.borrowbook.data.repo.BookRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<BookLocal>>
    val loadAvailableData: LiveData<List<BookLocal>>
    private val repository : BookRepo

    init {
        val bookDao = BorrowBookDatabase.getDatabase(application).bookDao()
        repository = BookRepo(bookDao)
        readAllData = repository.readAllData
        loadAvailableData = repository.loadAvailableBook
    }

    fun addBook(book: BookLocal){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBook(book)
        }
    }

    fun deleteAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllData()
        }
    }
}