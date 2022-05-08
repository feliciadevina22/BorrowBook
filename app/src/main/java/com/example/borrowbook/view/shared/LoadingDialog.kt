package com.example.borrowbook.view.shared

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.borrowbook.R

class LoadingDialog(private val fm: FragmentManager): DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_loading_dialog, container)
    }

    fun startLoading(){
        isCancelable = false
        show(fm, "loadingDialog")
    }

    fun stopLoading(){
        if (isVisible)
            dismiss()
    }
}