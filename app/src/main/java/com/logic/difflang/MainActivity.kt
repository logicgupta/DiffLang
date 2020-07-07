package com.logic.difflang

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.logic.difflang.utils.LocalManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocalManager.getPreferences(base!!))
    }

    var language :String=""
    var pos:Int=0
   lateinit var languages:Array<String>;
   lateinit var adapter:ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = resources.getString(R.string.app_name)
        languages = resources.getStringArray(R.array.array_name)

        // access the spinner
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
           adapter = ArrayAdapter(this,
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
            val intent=Intent(this,MessageActivity::class.java )
            val context:Context = LocalManager.getPreferences(this)!!
            val resources :Resources = context.resources
            when(pos){

                0-> Toast.makeText(this,resources.getString(R.string.select_lang),Toast.LENGTH_SHORT).show()
                1-> {
                    intent.putExtra("lang","en")
                   startActivity(intent)
                }
                2->{
                    intent.putExtra("lang","hi")
                   startActivity(intent)
                }
                3->{
                    intent.putExtra("lang","fr")
                    startActivity(intent)
                }
            }

        }
        settings.setOnClickListener {
            startActivity(Intent(this,SettingActivity::class.java))
        }
    }

    override fun onRestart() {
        super.onRestart()
        val context:Context = LocalManager.getPreferences(this)!!
        val resources :Resources = context.resources
        title = resources.getString(R.string.app_name)
        submit.text=resources.getString(R.string.submit)
        languages = resources.getStringArray(R.array.array_name)
        settings.text=resources.getString(R.string.settings)
        adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, languages)
        spinner.adapter = adapter
    }
}