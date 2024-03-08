package com.example.scanbite_v1_camera_function.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Layout
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
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.activity_login, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = Firebase.auth

        val register: TextView = view.findViewById(R.id.tvRegisterNow)

        register.setOnClickListener {
            val intent = Intent(requireActivity(), SignUpFragment::class.java)
            startActivity(intent)
        }

        val btnLogin: Button = view.findViewById(R.id.btnLogin)

        register.setOnClickListener{
            startActivity(Intent(requireActivity(), SignUpFragment::class.java))
        }

        btnLogin.setOnClickListener {
            performLogin(view)

        }
    }

    private fun performLogin(view: View) {
        val email: EditText = view.findViewById(R.id.etLoginEmail)
        val password: EditText = view.findViewById(R.id.etLoginPassword)

        if (email.text.isEmpty() || password.text.isEmpty()) {
            Toast.makeText(requireActivity(),"Please fill out the missing fields...", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val emailInput = email.text.toString()
        val passwordInput = password.text.toString()

        auth.signInWithEmailAndPassword(emailInput, passwordInput)
            .addOnCompleteListener(requireActivity(), object : OnCompleteListener<AuthResult> {
                override fun onComplete(task: Task<AuthResult>) {
                    if (task.isSuccessful) {
                        val intent = Intent(requireActivity(), MainActivity::class.java)
                        startActivity(intent)

                        Toast.makeText(requireActivity(), "Success.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(requireActivity(),"Authentication failed.",
                            Toast.LENGTH_SHORT,).show()
                    }
                }
            })
            .addOnFailureListener { e ->
                Toast.makeText(requireActivity(),"Authentication failed. ${e.localizedMessage}",
                    Toast.LENGTH_SHORT,).show()
            }
    }

    fun goToRegister(view: View) {
        // Get the child fragment manager
        val fragmentManager = childFragmentManager

        // Begin a new fragment transaction
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Replace the current fragment with the SignUpFragment
        // fragmentTransaction.replace(R.id.bottomNavigationView, SignUpFragment.newInstance())

        // Add the transaction to the back stack
        fragmentTransaction.addToBackStack(null)

        // Commit the transaction
        fragmentTransaction.commit()
    }
}