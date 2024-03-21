package com.example.scanbite_v1_camera_function

import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.scanbite_v1_camera_function.databinding.ActivityMainBinding
import com.example.scanbite_v1_camera_function.ui.gallery.CameraFragment
import com.example.scanbite_v1_camera_function.ui.home.HomeFragment
import com.example.scanbite_v1_camera_function.ui.login.LoginFragment
import com.example.scanbite_v1_camera_function.ui.review.ReviewFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(CameraFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){
                //Home Page
                R.id.home -> replaceFragment(HomeFragment())
                //Scan
                R.id.scan -> replaceFragment(CameraFragment())
                //    R.id.categories -> replaceFragment(CategoryFragment())
                R.id.reviews -> replaceFragment(ReviewFragment())
                //    R.id.favourites -> replaceFragment(FavouritesFragment())
                R.id.login -> replaceFragment(LoginFragment())
                else ->{
                }
            }
            true
        }
    }
    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }

}