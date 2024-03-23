package com.example.scanbite_v1_camera_function.signout

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.scanbite_v1_camera_function.R
import com.example.scanbite_v1_camera_function.databinding.ActivityMainBinding
import com.example.scanbite_v1_camera_function.ui.login.LoginFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignOutFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_sign_out, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Initialize Firebase Auth
        auth = Firebase.auth

        val loginText: TextView = view.findViewById(R.id.tvSignOut)
        loginText.setOnClickListener {
            // Perform fragment transaction here
            val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            val fragmentLoginFragment = LoginFragment()
            fragmentTransaction.replace(R.id.frame_layout, fragmentLoginFragment)
            fragmentTransaction.addToBackStack(null) // Optional: add to back stack
            fragmentTransaction.commit()
        }
    }
}