package com.sun.mvvm_pattern.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sun.mvvm_pattern.R
import com.sun.mvvm_pattern.screen.fragment.user.UserFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        UserFragment.getInstance()?.let {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, it, UserFragment::class.java.simpleName)
                .commit()
        }
    }
}
