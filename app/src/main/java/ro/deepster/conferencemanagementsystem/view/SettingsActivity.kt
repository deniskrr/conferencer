package ro.deepster.conferencemanagementsystem.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_settings.view.*
import ro.deepster.conferencemanagementsystem.R
import ro.deepster.conferencemanagementsystem.model.User

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
                //TODO If the values entered are correct, then create a new User object and write it to the database
                if (!textlayout_email_settings.editText?.text.isNullOrBlank()){
                    val email = textlayout_email_settings.editText?.text.toString()
                }
                if (!textlayout_user_name_settings.editText?.text.isNullOrBlank()){
                    val username = textlayout_user_name_settings.editText?.text.toString()
                }
                if (!textlayout_password_settings.editText?.text.isNullOrBlank()){
                    val password = textlayout_password_settings.editText?.text.toString()
                }
                if (!textlayout_name_settings.editText?.text.isNullOrBlank()){
                    val name = textlayout_name_settings.editText?.text.toString()
                }
                if (!textlayout_affiliation_settings.editText?.text.isNullOrBlank()){
                    val affiliation = textlayout_affiliation_settings.editText?.text.toString()
                }
                if (!textlayout_web_page_settings.editText?.text.isNullOrBlank()){
                    val web_page = textlayout_web_page_settings.editText?.text.toString()
                }


            }
        }
        return super.onOptionsItemSelected(item)
    }

}
