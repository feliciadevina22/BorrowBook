package com.example.borrowbook.viewInterface

import com.example.borrowbook.data.model.AllRecord
import com.example.borrowbook.data.model.Book
import com.example.borrowbook.data.model.BookLocal
import com.example.borrowbook.data.model.Loan

interface BookInterface {
    fun onSelected(book: BookLocal)
}

interface RecordInterface {
    fun onSelected(allRecord: AllRecord)
}