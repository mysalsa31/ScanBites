package com.example.scanbite_v1_camera_function.ui.singup

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
import com.example.scanbite_v1_camera_function.ui.login.LoginFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.activity_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Initialize Firebase Auth
        auth = Firebase.auth

        val loginText: TextView = view.findViewById(R.id.tvLoginNow)
        loginText.setOnClickListener {
            val intent = Intent(requireActivity(), LoginFragment::class.java)
            startActivity(intent)
        }

        val btnCreateAccount: Button = view.findViewById(R.id.btnCreateAccount)

        btnCreateAccount.setOnClickListener {
            performSignUp(view)
        }
    }

    private fun performSignUp(view: View) {
        val firstName = view.findViewById<EditText>(R.id.etFirstName)
        val lastName = view.findViewById<EditText>(R.id.etLastName)
        val email = view.findViewById<EditText>(R.id.etEmail)
        val password = view.findViewById<EditText>(R.id.etPassword)

        if (firstName.text.isEmpty() || lastName.text.isEmpty() || email.text.isEmpty() || password.text.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill out the missing fields...", Toast.LENGTH_SHORT)
                .show()
            return
        }
        //values for firebase
        val inputFirstName = firstName.text.toString()
        val inputLastName = lastName.text.toString()
        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()

        auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(requireActivity(), object : OnCompleteListener<AuthResult> {
                override fun onComplete(task: Task<AuthResult>) {
                    if (task.isSuccessful) {
                        val intent = Intent(requireActivity(), MainActivity::class.java)
                        startActivity(intent)

                        Toast.makeText(requireContext(), "Success.",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {
                        Toast.makeText(requireContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Error occurred ${it.localizedMessage}", Toast.LENGTH_SHORT)

            }
    }

    fun goToLogin(view: View) {
        startActivity(Intent(requireActivity(), LoginFragment::class.java))
    }
}