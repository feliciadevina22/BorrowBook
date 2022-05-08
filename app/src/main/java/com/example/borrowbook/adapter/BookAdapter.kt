package com.example.borrowbook.adapter

import android.annotation.SuppressLint
import android.content.Context
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.borrowbook.R
import com.example.borrowbook.data.model.BookLocal
import com.example.borrowbook.databinding.BookBinding
import com.example.borrowbook.view.home.CatalogueFragmentDirections
import com.example.borrowbook.viewInterface.BookInterface

class BookAdapter(
    var data: List<BookLocal>,
    val fragment: Fragment
): RecyclerView.Adapter<BookAdapter.ViewHolder>(), BookInterface {
    fun updateData(data: List<BookLocal>){
        this.data = data
        notifyItemRangeChanged(0, data.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<BookBinding>(layoutInflater, R.layout.item_book_card, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.listener = this

        val book = data[position]
        holder.bind(book, fragment.requireContext())
    }

    inner class ViewHolder(val binding: BookBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(book: BookLocal, context: Context){
            binding.book = book

            binding.tvBookCardAuthor.setText(book.authors)
            binding.tvBookCardPage.setText("${book.page_count} Page")
        }
    }

    override fun onSelected(book: BookLocal) {
        val action = CatalogueFragmentDirections.actionCatalogueFragmentToBookDetailFragment(book)
        action?.let { fragment.findNavController().navigate(it) }
    }
}