package com.examples.userauthentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

class AuthenticationCheckFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_authentication_check, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkAuthenticationState()
    }

    private fun checkAuthenticationState() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            findNavController().navigate(AuthenticationCheckFragmentDirections.actionAuthenticationCheckFragmentToNavMain())
        } else {
            findNavController().navigate(AuthenticationCheckFragmentDirections.actionAuthenticationCheckFragmentToLoginFragment())
        }
    }
}