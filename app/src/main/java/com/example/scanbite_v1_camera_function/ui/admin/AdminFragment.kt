package com.example.scanbite_v1_camera_function.ui.admin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.scanbite_v1_camera_function.MainActivity
import com.example.scanbite_v1_camera_function.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AdminFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        val btnAdminLogin: Button = view.findViewById(R.id.btnAdminLogin)

        btnAdminLogin.setOnClickListener {
            performAdminLogin(view)
        }
    }

    private fun performAdminLogin(view: View) {
        val email: EditText = view.findViewById(R.id.etLoginEmail)
        val password: EditText = view.findViewById(R.id.etLoginPassword)

        if (email.text.isEmpty() || password.text.isEmpty()) {
            Toast.makeText(
                requireActivity(),
                "Please fill out the missing fields...",
                Toast.LENGTH_SHORT
            )
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
}