package com.example.borrowbook.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.borrowbook.R
import com.example.borrowbook.data.model.BookLocal
import kotlinx.android.synthetic.main.fragment_book_detail.*
import kotlinx.android.synthetic.main.toolbar_default.*

class BookDetailFragment : Fragment() {
    private var book: BookLocal? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()

        toolbar_default.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupView() {
        arguments?.let {
            book = BookDetailFragmentArgs.fromBundle(it).book
        }
        setupBook()
    }

    private fun setupBook() {
        Glide.with(this.requireView())
            .load(book?.thumbnail_url)
            .placeholder(R.drawable.ic_baseline_hide_image_24)
            .error(R.drawable.ic_baseline_hide_image_24)
            .into(iv_book_detail_pic)

        tv_book_detail_title.setText(book?.title)

        tv_book_detail_author.setText(book?.authors)
        tv_book_detail_page.setText("${book?.page_count} Page")
        tv_book_detail_more_about.setText(book?.long_description)
        tv_book_detail_isbn.setText("ISBN : ${book?.isbn}")

        if (book?.loan_id == null) {
            tv_book_loan_status.setText("You Can Loan the Book :D")
        } else {
            tv_book_loan_status.setText("Book is not Available :(\nCome Again")
        }
    }
}