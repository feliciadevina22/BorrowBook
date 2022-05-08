package com.example.borrowbook.data.repo

import androidx.lifecycle.LiveData
import com.example.borrowbook.data.model.AllRecord
import com.example.borrowbook.data.model.BookLocal
import com.example.borrowbook.data.model.Loan
import com.example.borrowbook.data.rest.BookDao
import com.example.borrowbook.data.rest.LoanDao

class LoanRepo(private val loanDao: LoanDao, private val bookDao: BookDao) {

    val readAllData: LiveData<List<Loan>> = loanDao.readAllData()
    val showAllRecord: LiveData<List<AllRecord>> = loanDao.getRecordData()

    suspend fun addLoan(loan: Loan, book: BookLocal){
        loanDao.createLoan(loan)
        bookDao.updateBook(book)
    }

    suspend fun returnLoan(loan: Loan, book: BookLocal){
        loanDao.updateLoan(loan)
        bookDao.updateBook(book)
    }

    suspend fun deleteLoan(loan: Loan, book: BookLocal){
        book.loan_id = null
        bookDao.updateBook(book)
        loanDao.deleteLoan(loan)
    }
}