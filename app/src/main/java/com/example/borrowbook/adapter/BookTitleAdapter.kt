package com.example.borrowbook.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.borrowbook.R
import com.example.borrowbook.data.model.BookLocal
import com.example.borrowbook.databinding.BookBinding
import com.example.borrowbook.databinding.BookTitleBinding
import com.example.borrowbook.view.home.CatalogueFragmentDirections
import com.example.borrowbook.viewInterface.BookInterface

class BookTitleAdapter(
    var data: List<BookLocal>,
    val fragment: Fragment,
    val bookInterface: BookInterface
): RecyclerView.Adapter<BookTitleAdapter.ViewHolder>(){
    fun updateData(data: List<BookLocal>){
        this.data = data
        notifyItemRangeChanged(0, data.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<BookTitleBinding>(layoutInflater, R.layout.item_book_title, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.listener = bookInterface

        val book = data[position]
        holder.bind(book, fragment.requireContext())
    }

    inner class ViewHolder(val binding: BookTitleBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(book: BookLocal, context: Context){
            binding.book = book
        }
    }
}