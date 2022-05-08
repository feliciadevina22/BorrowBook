package com.example.borrowbook.view.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.borrowbook.R
import com.example.borrowbook.adapter.BookAdapter
import com.example.borrowbook.data.model.Book
import com.example.borrowbook.data.model.BookLocal
import com.example.borrowbook.view.shared.LoadingDialog
import com.example.borrowbook.viewModel.BookViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_catalogue.*
import java.io.IOException

class CatalogueFragment : Fragment() {
    private var loadingDialog: LoadingDialog? = null
    private lateinit var bookVM: BookViewModel

    private var bookAdapter: BookAdapter? = null

    private var bookData = arrayListOf<BookLocal>()
    private var bookJson = arrayListOf<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_catalogue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()

    }

    private fun setupView() {
        bookVM = ViewModelProvider(this).get(BookViewModel::class.java)
        setupAdapter()
        bookVM.readAllData.observe(viewLifecycleOwner, Observer {
            if (it.count() > 0) {
                bookData = it as ArrayList<BookLocal>
                bookAdapter!!.updateData(bookData)
            } else {
                getData()
            }
        })
    }

    private fun setupAdapter() {
        bookAdapter = BookAdapter(arrayListOf(), this)

        rv_book.apply {
            swapAdapter(bookAdapter, true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            hasFixedSize()
        }
    }

    private fun addDatatoDatabase(book: Book) {
        var authors = ""
        for (i in book.authors) {
            if (authors == "") {
                authors += i
            } else {
                authors += ", $i"
            }
        }

        val newBook = BookLocal(0, book.title, book.isbn, book.pageCount, book.thumbnailUrl, book.shortDescription, book.longDescription, authors, loan_id = null)

        bookVM.addBook(newBook)
    }

    private fun getData() {
        val jsonFileString = getJsonDataFromAsset(requireContext())
        val gson = Gson()
        val listBookType = object : TypeToken<List<Book>>() {}.type
        bookJson = gson.fromJson(jsonFileString, listBookType)

        bookJson.forEach {
            addDatatoDatabase(it)
        }
    }

    fun getJsonDataFromAsset(context: Context): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open("books.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}