package com.example.stuinfosystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.stuinfosystem.databinding.ActivityStuBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class StuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityStuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val stuNavView: BottomNavigationView = binding.stuNavView
        val navController = findNavController(R.id.nav_host_fragment_activity_stu)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.stuScoreFragment,R.id.classmatesFragment,R.id.teaSettingFragment2
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        stuNavView.setupWithNavController(navController)

        val name = intent.getStringExtra("userName")
        //Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
    }
}