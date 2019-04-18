package ro.deepster.conferencemanagementsystem.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*
import ro.deepster.conferencemanagementsystem.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        title = "Settings"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_save_settings -> {
                textlayout_name_settings.error = null
                textlayout_web_page_settings.error = null
                textlayout_affiliation_settings.error = null

                if (textlayout_name_settings.editText?.text.isNullOrBlank()) {
                    textlayout_name_settings.error = "Name can't be empty"
                }
                if (textlayout_affiliation_settings.editText?.text.isNullOrBlank()) {
                    textlayout_affiliation_settings.error = "Affiliation can't be empty"
                }
                if (textlayout_web_page_settings.editText?.text.isNullOrBlank()) {
                    textlayout_web_page_settings.error = "Web page can't be empty"
                }


            }
        }
        return super.onOptionsItemSelected(item)
    }

}
