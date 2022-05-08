package com.example.borrowbook.data.repo

import androidx.lifecycle.LiveData
import com.example.borrowbook.data.model.BookLocal
import com.example.borrowbook.data.model.User
import com.example.borrowbook.data.rest.BookDao
import com.example.borrowbook.data.rest.UserDao

class BookRepo(private val bookDao: BookDao) {
    val readAllData: LiveData<List<BookLocal>> = bookDao.readAllData()
    val loadAvailableBook: LiveData<List<BookLocal>> = bookDao.loadAvailableBook()

    suspend fun addBook(book: BookLocal){
        bookDao.addBook(book)
    }

    fun deleteAllData(){
        bookDao.deleteAllData()
    }
}