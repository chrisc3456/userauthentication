package com.examples.userauthentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private val authentication = FirebaseAuth.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLogin()
        setupRegistration()
        setupForgottenPassword()
    }

    private fun setupLogin() {
        button_login.setOnClickListener {
            val email = edittext_email.text.toString()
            val password = edittext_password.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Please fill out all fields", Toast.LENGTH_LONG).show()
            } else {
                authentication.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToNavMain())
                    } else {
                        Toast.makeText(context, "Login failed", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun setupRegistration() {
        button_register.setOnClickListener {
            val email = edittext_email.text.toString()
            val password = edittext_password.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Please fill out all fields", Toast.LENGTH_LONG).show()
            } else {
                authentication.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToNavMain())
                    } else {
                        Toast.makeText(context, "Login failed", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun setupForgottenPassword() {
        textview_forgotten_password.setOnClickListener {
            val email = edittext_email.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(context, "Please provide your email address", Toast.LENGTH_LONG).show()
            } else {
                authentication.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(context, "Reset link sent to your email", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, "Unable to send reset mail", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}