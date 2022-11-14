package com.example.digitalsociety.members

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.digitalsociety.R
import com.example.digitalsociety.members.login.SmLoginFragment
import com.example.digitalsociety.members.registration.SmRegistrationFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class SmRegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sm_registration)

        var bnview=findViewById<BottomNavigationView>(R.id.bn_view)

        loadFragment(SmRegistrationFragment())
        bnview.setOnItemSelectedListener {
            when(it.itemId){
                R.id.regi_page ->{
                    loadFragment(SmRegistrationFragment())
                }
                R.id.login_page ->{
                    loadFragment(SmLoginFragment())
                }
            }

            return@setOnItemSelectedListener true
        }


    }

    fun loadFragment(fragment: Fragment)
    {
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.replace_new,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}