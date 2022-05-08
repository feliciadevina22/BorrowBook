package com.example.borrowbook.data.rest

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.borrowbook.data.model.BookLocal

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBook(book: BookLocal)

    @Query("SELECT * FROM book_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<BookLocal>>

    @Query("SELECT * FROM book_table WHERE loan_id IS NULL ORDER BY id ASC")
    fun loadAvailableBook(): LiveData<List<BookLocal>>

    @Update
    suspend fun updateBook(book: BookLocal)

    @Query("DELETE FROM book_table")
    fun deleteAllData()
}