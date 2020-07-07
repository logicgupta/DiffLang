package com.logic.difflang

import android.content.Context
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.logic.difflang.utils.LocalManager
import kotlinx.android.synthetic.main.activity_message.*

class MessageActivity : AppCompatActivity() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocalManager.getPreferences(base!!))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

       val lang:String= intent.getStringExtra("lang")
       val context:Context = LocalManager.updateResources(this, lang)!!
       val resources :Resources = context.resources
        title = resources.getString(R.string.app_name)
        textView.text=resources.getString(R.string.message)

    }
}