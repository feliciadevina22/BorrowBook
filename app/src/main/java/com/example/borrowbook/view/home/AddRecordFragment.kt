package com.example.borrowbook.view.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.borrowbook.R
import com.example.borrowbook.adapter.BookAdapter
import com.example.borrowbook.adapter.BookTitleAdapter
import com.example.borrowbook.data.model.BookLocal
import com.example.borrowbook.data.model.Loan
import com.example.borrowbook.data.model.User
import com.example.borrowbook.extension.getCurrentDate
import com.example.borrowbook.extension.getDueDate
import com.example.borrowbook.viewInterface.BookInterface
import com.example.borrowbook.viewModel.BookViewModel
import com.example.borrowbook.viewModel.LoanViewModel
import com.example.borrowbook.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add_record.*
import kotlinx.android.synthetic.main.fragment_catalogue.*
import kotlinx.android.synthetic.main.toolbar_default.*
import java.util.*
import kotlin.collections.ArrayList

class AddRecordFragment : Fragment(), BookInterface {
    private var isAddBook = false
    private var bookData = arrayListOf<BookLocal>()
    private var bookLoan = arrayListOf<BookLocal>()
    private lateinit var bookVM: BookViewModel
    private lateinit var userVM: UserViewModel
    private lateinit var loanVM: LoanViewModel

    private var bookTitleAdapter : BookTitleAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_record, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()

        toolbar_default.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        btn_loan_add_book.setOnClickListener {
            isAddBook = !isAddBook
            rv_loan_books.isVisible = isAddBook
            if (isAddBook) { btn_loan_add_book.setText("Hide Add Book") } else { btn_loan_add_book.setText("Show Add Book") }
        }

        btn_loan.setOnClickListener {
            if (bookLoan.isNotEmpty() && edit_loan_name.text!!.isNotEmpty() && edit_loan_number.text!!.isNotEmpty()) {
                val user = User(edit_loan_number.text.toString(), edit_loan_name.text.toString(), )
                val date = Date()
                createNewLoan(user, date)
            } else {
                Toast.makeText(requireContext(), "Please Fill All the Blank", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun createNewLoan(user : User, date: Date){
        userVM.readAllData.observe(viewLifecycleOwner, Observer {
            val userCheck = it.firstOrNull { it.id == user.id }
            if (userCheck != null){
                bookLoan.forEach {
                    val loan = Loan(0, userCheck.id, it.id, getCurrentDate(date), getDueDate(date), null)
                    it.loan_id = userCheck.id
                    loanVM.addLoan(loan, it)
                    Toast.makeText(requireContext(), "Loan Succes", Toast.LENGTH_LONG).show()
                }
                findNavController().popBackStack()
            } else {
                userVM.addUser(user)
            }
        })
    }

    private fun setupView() {
        bookVM = ViewModelProvider(this).get(BookViewModel::class.java)
        userVM = ViewModelProvider(this).get(UserViewModel::class.java)
        loanVM = ViewModelProvider(this).get(LoanViewModel::class.java)
        setupAdapter()
    }

    private fun setupAdapter() {
        bookTitleAdapter = BookTitleAdapter(arrayListOf(), this, this)

        rv_loan_books.apply {
            swapAdapter(bookTitleAdapter, true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            hasFixedSize()
        }

        bookVM.loadAvailableData.observe(viewLifecycleOwner, Observer {
            bookData = it as ArrayList<BookLocal>
            bookTitleAdapter!!.updateData(bookData)
        })
    }

    override fun onSelected(book: BookLocal) {
        if (bookLoan.contains(book)){
            bookLoan.remove(book)
        }else {
            bookLoan.add(book)
        }

        updateText()
    }

    private fun updateText(){
        var loanBook = ""

        for (i in bookLoan) {
            if (loanBook == "") {
                loanBook += "${i.id} - ${i.title}"
            } else {
                loanBook += "\n${i.id} - ${i.title}"
            }
        }

        tv_book_loan.setText(loanBook)
    }
}