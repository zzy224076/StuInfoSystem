package com.example.stuinfosystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.stuinfosystem.databinding.ActivityAdminBinding
import com.example.stuinfosystem.databinding.ActivityTeaBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class TeaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTeaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTeaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_tea)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.teaPutinFragment, R.id.teaSettingFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val name = intent.getStringExtra("userName")
        //Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
    }
}