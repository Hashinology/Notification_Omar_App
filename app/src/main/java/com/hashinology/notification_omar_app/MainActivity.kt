package com.hashinology.notification_omar_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hashinology.notification_omar_app.ui.EggTimerFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, EggTimerFragment.newInstance())
                .commitNow()
        }
    }
}