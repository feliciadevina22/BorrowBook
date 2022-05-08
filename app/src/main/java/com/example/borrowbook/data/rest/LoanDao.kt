package com.example.borrowbook.data.rest

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.borrowbook.data.model.AllRecord
import com.example.borrowbook.data.model.BookLocal
import com.example.borrowbook.data.model.Loan

@Dao
interface LoanDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createLoan(loan: Loan)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateLoan(loan: Loan)

    @Delete
    suspend fun deleteLoan(loan: Loan)

    @Query("SELECT * FROM loan_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Loan>>

    @Query(
        "SELECT loan_table.* FROM loan_table " +
                "INNER JOIN book_table ON loan_table.book_id = book_table.id " +
                "INNER JOIN user_table ON loan_table.user_id = user_table.id"
    )
    fun getRecordData(): LiveData<List<AllRecord>>
}