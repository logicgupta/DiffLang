package com.logic.difflang.utils
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import java.util.*

class LocalManager {
        companion object {

            fun  setPreferences(context: Context,language: String): Context? {
                val editor =context.getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
                editor.putString("My_Lang", language)
                editor.apply()
               return updateResources(context,language)
            }

            fun getPreferences(context: Context): Context? {
                val sharedPreferences = context.getSharedPreferences("Settings", 0)
                val language = sharedPreferences.getString("My_Lang", "")
                  return  updateResources(context,language!!)

            }

             fun updateResources(context: Context, language: String): Context? {
                var context: Context = context
                val locale = Locale(language)
                Locale.setDefault(locale)
                val res: Resources = context.resources
                val config = Configuration(res.configuration)
                if (Build.VERSION.SDK_INT >= 17) {
                    config.setLocale(locale)
                    context = context.createConfigurationContext(config)
                } else {
                    config.locale = locale
                    res.updateConfiguration(config, res.displayMetrics)
                }
                return context
            }
        }
}