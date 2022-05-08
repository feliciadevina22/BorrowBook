package com.example.borrowbook.view.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.borrowbook.R
import com.example.borrowbook.adapter.BookAdapter
import com.example.borrowbook.adapter.RecordAdapter
import com.example.borrowbook.data.model.AllRecord
import com.example.borrowbook.data.model.Book
import com.example.borrowbook.data.model.BookLocal
import com.example.borrowbook.viewModel.BookViewModel
import com.example.borrowbook.viewModel.LoanViewModel
import kotlinx.android.synthetic.main.fragment_catalogue.*
import kotlinx.android.synthetic.main.fragment_record_list.*

class RecordListFragment : Fragment() {
    private lateinit var loanVM: LoanViewModel

    private var recordAdapter: RecordAdapter? = null
    private var allRecord = arrayListOf<AllRecord>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_record_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()

        btn_add_record.setOnClickListener {
            findNavController().navigate(R.id.action_recordListFragment_to_addRecordFragment)
        }
    }

    private fun setupView() {
        loanVM = ViewModelProvider(this).get(LoanViewModel::class.java)
        setupAdapter()
    }

    private fun setupAdapter() {
        recordAdapter = RecordAdapter(arrayListOf(), this)

        rv_records.apply {
            swapAdapter(recordAdapter, true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            hasFixedSize()
        }

        loanVM.readAllRecord.observe(viewLifecycleOwner, Observer {
            allRecord = it as ArrayList<AllRecord>
            recordAdapter!!.updateData(allRecord)
        })
    }
}