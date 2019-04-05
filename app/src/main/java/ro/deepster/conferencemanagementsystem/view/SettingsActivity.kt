package ro.deepster.conferencemanagementsystem.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ro.deepster.conferencemanagementsystem.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setTitle("Settings")
    }
}
