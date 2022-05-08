package com.example.borrowbook.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.borrowbook.data.database.BorrowBookDatabase
import com.example.borrowbook.data.model.AllRecord
import com.example.borrowbook.data.model.BookLocal
import com.example.borrowbook.data.model.Loan
import com.example.borrowbook.data.repo.LoanRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoanViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Loan>>
    val readAllRecord: LiveData<List<AllRecord>>
    private val repository : LoanRepo

    init {
        val loanDao = BorrowBookDatabase.getDatabase(application).loanDao()
        val bookDao = BorrowBookDatabase.getDatabase(application).bookDao()
        repository = LoanRepo(loanDao, bookDao)
        readAllData = repository.readAllData
        readAllRecord = repository.showAllRecord
    }

    fun addLoan(loan: Loan, book: BookLocal){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addLoan(loan, book)
        }
    }

    fun returnLoan(loan: Loan, book: BookLocal){
        viewModelScope.launch(Dispatchers.IO) {
            repository.returnLoan(loan, book)
        }
    }

    fun deleteLoan(loan: Loan, book: BookLocal){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteLoan(loan, book)
        }
    }
}