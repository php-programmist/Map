package ru.phpprogrammist.map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_settings -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container,SettingsFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_map -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container,MapFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        if(savedInstanceState == null) {
            navigation.selectedItemId = R.id.navigation_settings
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,SettingsFragment()).commit()
        }

    }
}
