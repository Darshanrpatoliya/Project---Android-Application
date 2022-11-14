package com.example.digitalsociety.members.login

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.digitalsociety.R
import com.example.digitalsociety.members.login.smloginafter.notice.NoticeFragment
import com.example.digitalsociety.members.login.smloginafter.profile.ProfileSmFragment
import com.example.digitalsociety.members.registration.SmRegistrationFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class WcSmOnlyActivity : AppCompatActivity()
{

    lateinit var appBarConfiguration: AppBarConfiguration

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wc_sm_only)

        var bnview_two=findViewById<BottomNavigationView>(R.id.bn_view_two)

        loadFragment(NoticeFragment())

        bnview_two.setOnItemSelectedListener {
            when(it.itemId){
                R.id.noticeFragment ->{
                    loadFragment(NoticeFragment())
                }
                R.id.profileFragment ->{
                    loadFragment(ProfileSmFragment())
                }
            }

            return@setOnItemSelectedListener true
        }


    }
    fun loadFragment(fragment: Fragment)
    {
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.replace_new_two,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}