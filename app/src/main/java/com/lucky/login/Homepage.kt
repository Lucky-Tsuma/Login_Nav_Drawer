package com.lucky.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.lucky.login.databinding.ActivityHomepageBinding

class Homepage : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityHomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        binding = ActivityHomepageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbarHomepage)
        /*setting the toolbar we created to be the default action bar for this activity.*/

        val toggle = ActionBarDrawerToggle(
            this, binding.drawerlayoutHomepage, binding.toolbarHomepage,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        binding.drawerlayoutHomepage.addDrawerListener(toggle)
        toggle.syncState()/*rotates the hamburger icon*/
        /*An instance of ActionbarToggle is responsible for bringing in the navigation drawer*/

        binding.navViewHomepage.setNavigationItemSelectedListener(this)
        /*To be able to handle click events on the navigation view(the one with menu and header)*/

        val sh = getSharedPreferences("sharedPref", MODE_PRIVATE)

        val header = binding.navViewHomepage.getHeaderView(0)
        val header_email = header.findViewById<TextView>(R.id.user_email)
        header_email.text = sh.getString("email", null)
    }

    override fun onBackPressed() {
        if (binding.drawerlayoutHomepage.isDrawerOpen(GravityCompat.START)) {
            binding.drawerlayoutHomepage.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_account -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_homepage, FragmentAccount()).commit()
            }
            R.id.item_settings -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_homepage, FragmentSettings()).commit()
            }
            R.id.item_notifications -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_homepage, FragmentNotifications()).commit()
            }
            R.id.item_logout -> {
                val sh = getSharedPreferences("sharedPref", MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sh.edit()
                editor.clear()

                Toast.makeText(this, "You have been logged out!", Toast.LENGTH_SHORT).show()
                val intentLogin = Intent(applicationContext, MainActivity::class.java)
                startActivity(intentLogin)
            }
        }

        binding.drawerlayoutHomepage.closeDrawer(GravityCompat.START)
        return true
    }
}