package com.example.borrowbook.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.borrowbook.R
import com.example.borrowbook.data.model.AllRecord
import com.example.borrowbook.data.model.BookLocal
import com.example.borrowbook.data.model.Loan
import com.example.borrowbook.databinding.BookTitleBinding
import com.example.borrowbook.databinding.RecordBinding
import com.example.borrowbook.extension.formatDateText
import com.example.borrowbook.view.home.CatalogueFragmentDirections
import com.example.borrowbook.view.home.RecordListFragmentDirections
import com.example.borrowbook.viewInterface.BookInterface
import com.example.borrowbook.viewInterface.RecordInterface

class RecordAdapter(
    var data: List<AllRecord>,
    val fragment: Fragment
): RecyclerView.Adapter<RecordAdapter.ViewHolder>(), RecordInterface {
    fun updateData(data: List<AllRecord>){
        this.data = data
        notifyItemRangeChanged(0, data.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RecordBinding>(layoutInflater, R.layout.item_record, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.listener = this

        val record = data[position]
        holder.bind(record, fragment.requireContext())
    }

    inner class ViewHolder(val binding: RecordBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(allRecord: AllRecord, context: Context){
            binding.allRecord = allRecord

            binding.tvRecordId.setText("${allRecord.record.id}")
            binding.tvRecordBook.setText("${allRecord.book.id} - ${allRecord.book.title}")
            binding.tvRecordUser.setText("${allRecord.user.name} - ${allRecord.user.id}")

            binding.tvRecordDate.setText("Loan Date : ${allRecord.record.loan_date.formatDateText()}")
            binding.tvRecordDue.setText("Due Date : ${allRecord.record.due_date.formatDateText()}")

            if (allRecord.record.return_date != null) {
                binding.tvRecordReturn.setText("Return Date : ${allRecord.record.return_date!!.formatDateText()}")
            } else {
                binding.tvRecordReturn.setText("Return Date : -")
            }

        }
    }

    override fun onSelected(allRecord: AllRecord) {
        val action = RecordListFragmentDirections.actionRecordListFragmentToRecordDetailFragment(allRecord)
        action?.let { fragment.findNavController().navigate(it) }
    }
}