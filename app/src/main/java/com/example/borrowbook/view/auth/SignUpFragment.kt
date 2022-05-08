package com.example.borrowbook.view.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.borrowbook.R
import com.example.borrowbook.view.shared.LoadingDialog
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.toolbar_default.*

class SignUpFragment : Fragment() {
    private var loadingDialog : LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView()

        toolbar_default.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        btn_create_account.setOnClickListener {
            if ( edit_sign_up_name.text!!.isEmpty() || edit_sign_up_email.text!!.isEmpty() || edit_sign_up_password.text!!.isEmpty()  ) {
                failedTextField("Please Fill All the Field")
            } else {
                loadingDialog!!.startLoading()
                normalTextField()
                createAccount()
            }
        }
    }

    private fun setUpView() {
        loadingDialog = LoadingDialog(childFragmentManager)
    }

    fun failedTextField(errorMsg: String) {
        tv_sign_up_warning.isVisible = true
        tv_sign_up_warning.setText(errorMsg)
    }

    fun normalTextField() {
        tv_sign_up_warning.isVisible = false
    }

    private fun createAccount() {
        loadingDialog?.stopLoading()
        Log.d("", "Create Account")
    }
}