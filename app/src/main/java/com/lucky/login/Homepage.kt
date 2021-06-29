package com.lucky.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.lucky.login.databinding.ActivityHomepageBinding

class Homepage : AppCompatActivity() {
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
    }

    override fun onBackPressed() {
        if (binding.drawerlayoutHomepage.isDrawerOpen(GravityCompat.START)) {
            binding.drawerlayoutHomepage.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}