package com.example.borrowbook.view.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.borrowbook.R
import com.example.borrowbook.view.home.HomeActivity
import com.example.borrowbook.view.shared.LoadingDialog
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : Fragment() {
    private var loadingDialog : LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView()

        btn_sing_in.setOnClickListener {
            if (edit_sign_in_email.text!!.isEmpty() || edit_sign_in_password.text!!.isEmpty() ) {
                failedTextField("Please Fill All the Field")
            } else {
                loadingDialog!!.startLoading()
                login()
            }
        }

        btn_sign_up.setOnClickListener {
            clearTextField()
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        btn_skip.setOnClickListener {
            val intent = Intent(context, HomeActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

    private fun setUpView() {
        loadingDialog = LoadingDialog(childFragmentManager)
    }

    fun failedTextField(errorMsg: String) {
        tv_warning.isVisible = true
        tv_warning.setText(errorMsg)
    }

    fun normalTextField() {
        tv_warning.isVisible = false
    }

    fun clearTextField() {
        normalTextField()
        edit_sign_in_email.setText("")
        edit_sign_in_password.setText("")
    }

    private fun login() {
        loadingDialog?.stopLoading()
        Log.d("Login", "Muehehe")
    }
}