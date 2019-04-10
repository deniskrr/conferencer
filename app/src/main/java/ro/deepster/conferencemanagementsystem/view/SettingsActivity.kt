package ro.deepster.conferencemanagementsystem.view

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import ro.deepster.conferencemanagementsystem.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setTitle("Settings")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

}
