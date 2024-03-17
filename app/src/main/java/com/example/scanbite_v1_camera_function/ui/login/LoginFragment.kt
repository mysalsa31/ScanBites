package com.example.scanbite_v1_camera_function.ui.login;

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.scanbite_v1_camera_function.MainActivity
import com.example.scanbite_v1_camera_function.R
import com.example.scanbite_v1_camera_function.ui.singup.SignUpFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = Firebase.auth

        val register: TextView = view.findViewById(R.id.tvRegisterNow)
        val btnLogin: Button = view.findViewById(com.example.scanbite_v1_camera_function.R.id.btnLogin)

        register.setOnClickListener {
            // Perform fragment transaction here
            val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            val fragmentSignUpFragment = SignUpFragment()
            fragmentTransaction.replace(R.id.frame_layout, fragmentSignUpFragment )
            fragmentTransaction.addToBackStack(null) // Optional: add to back stack
            fragmentTransaction.commit()
        }


        btnLogin.setOnClickListener {
            performLogin(view)
        }
    }

    private fun performLogin(view: View) {
        val email: EditText = view.findViewById(R.id.etLoginEmail)
        val password: EditText = view.findViewById(R.id.etLoginPassword)

        if (email.text.isEmpty() || password.text.isEmpty()) {
            Toast.makeText(requireActivity(),"Please fill out the missing fields...", Toast.LENGTH_SHORT).show()
            return
        }

        val emailInput = email.text.toString()
        val passwordInput = password.text.toString()

        auth.signInWithEmailAndPassword(emailInput, passwordInput)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(requireActivity(), "Success.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireActivity(),"Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireActivity(),"Authentication failed. ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
    }
}
