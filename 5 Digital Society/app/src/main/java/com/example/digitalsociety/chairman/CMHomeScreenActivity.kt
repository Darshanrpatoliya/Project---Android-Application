package com.example.digitalsociety.chairman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.digitalsociety.R
import com.google.android.material.navigation.NavigationView

class CMHomeScreenActivity : AppCompatActivity() {

    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cmhome_screen)

        var drawerLayout: DrawerLayout = findViewById(R.id.main_draw_layout)
        var nav_view: NavigationView = findViewById(R.id.nav_view)
        var toolbar:androidx.appcompat.widget.Toolbar=findViewById(R.id.nav_toolbar)
        setSupportActionBar(toolbar)
        var navController = findNavController(R.id.FragmentConView)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.requestYesFragment,
                R.id.allShowMembersFragment,
                R.id.logOutFragment
            ),drawerLayout)

        setupActionBarWithNavController(navController,appBarConfiguration)
        nav_view.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        var navController=findNavController(R.id.FragmentConView)

        return navController.navigateUp(appBarConfiguration) || return super.onSupportNavigateUp()
    }
}