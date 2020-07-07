package com.logic.difflang

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.logic.difflang.utils.LocalManager
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

    var language :String=""
    var pos:Int=0

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocalManager.getPreferences(base!!))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        title = resources.getString(R.string.app_name)
        text_de.text = resources.getString(R.string.set_the_language_as_default)


        val languages = resources.getStringArray(R.array.array_name)

        // access the spinner
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, languages)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    language=languages[position]
                    pos=position

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
        }

        submit.setOnClickListener {
            val context:Context = LocalManager.getPreferences(this)!!
            val resources : Resources = context.resources
            when(pos){

                0-> Toast.makeText(this,resources.getString(R.string.select_lang),Toast.LENGTH_SHORT).show()
                1->    {
                    LocalManager.setPreferences(this,"en")
                    Toast.makeText(this,resources.getString(R.string.select_lang_success),Toast.LENGTH_SHORT).show()
                    finish()
                }
                2-> {
                    LocalManager.setPreferences(this,"hi")
                    Toast.makeText(this,resources.getString(R.string.select_lang_success),Toast.LENGTH_SHORT).show()
                    finish()
                }
                3-> {
                    LocalManager.setPreferences(this,"fr")
                    Toast.makeText(this,resources.getString(R.string.select_lang_success),Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}