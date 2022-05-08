package com.example.borrowbook.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.borrowbook.R
import com.example.borrowbook.data.model.AllRecord
import com.example.borrowbook.extension.formatDateText
import com.example.borrowbook.extension.getCurrentDate
import com.example.borrowbook.viewModel.BookViewModel
import com.example.borrowbook.viewModel.LoanViewModel
import com.example.borrowbook.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_record_detail.*
import kotlinx.android.synthetic.main.toolbar_default.*
import java.util.*

class RecordDetailFragment : Fragment() {
    private var allRecord : AllRecord? = null
    private lateinit var bookVM: BookViewModel
    private lateinit var userVM: UserViewModel
    private lateinit var loanVM: LoanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_record_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar_default.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        btn_return_book.setOnClickListener {
            val date = Date()
            allRecord!!.record.return_date = getCurrentDate(date)
            allRecord!!.book.loan_id = null
            loanVM.returnLoan(allRecord!!.record, allRecord!!.book)
            Toast.makeText(requireContext(), "Return Book Success", Toast.LENGTH_LONG).show()
            setupView()
        }

        setupView()
    }

    private fun setupView(){
        bookVM = ViewModelProvider(this).get(BookViewModel::class.java)
        userVM = ViewModelProvider(this).get(UserViewModel::class.java)
        loanVM = ViewModelProvider(this).get(LoanViewModel::class.java)

        arguments?.let {
            allRecord = RecordDetailFragmentArgs.fromBundle(it).allRecord
        }

        setupPage()
    }

    private fun setupPage() {
        tv_record_detail_user.setText("${allRecord!!.user.name} - ${allRecord!!.user.id}")
        tv_record_detail_date.setText("Loan Date : ${allRecord!!.record.loan_date.formatDateText()}")
        tv_record_detail_due.setText("Due Date : ${allRecord!!.record.due_date.formatDateText()}")

        if (allRecord!!.record.return_date != null) {
            tv_record_detail_return.setText("Return Date : ${allRecord!!.record.return_date!!.formatDateText()}")
            btn_return_book.isVisible = false
        } else {
            tv_record_detail_return.setText("Return Date : -")
            btn_return_book.isVisible = true
        }

        Glide.with(this.requireView())
            .load(allRecord!!.book?.thumbnail_url)
            .placeholder(R.drawable.ic_baseline_hide_image_24)
            .error(R.drawable.ic_baseline_hide_image_24)
            .into(iv_record_detail_pic)

        tv_record_detail_title.setText(allRecord!!.book?.title)

        tv_record_detail_author.setText(allRecord!!.book?.authors)
        tv_record_detail_page.setText("${allRecord!!.book?.page_count} Page")
        tv_record_detail_more_about.setText(allRecord!!.book?.long_description)
        tv_record_detail_isbn.setText("ISBN : ${allRecord!!.book?.isbn}")
    }
}