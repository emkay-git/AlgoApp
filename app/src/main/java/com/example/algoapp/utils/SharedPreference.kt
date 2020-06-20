package com.example.algoapp.utils

import android.content.Context
import android.content.SharedPreferences


class AlgoAppPreferences {

    companion object {
        const val LAYOUT_TYPE = "layout_type"
        // this creates an xml file where i will hold the key value pair. default is the name of the file
        // 0 defines it is accessible anywhere
        // each preference object represent on file.
        private fun preferences(context: Context): SharedPreferences  = context.getSharedPreferences("default",0)

        fun setPreferences(context:Context,key:String, type: String) {
            preferences(context)
                .edit()
                .putString(key,type)
                .apply()
        }

        fun getPreferences(context: Context,key:String) : String {
           return preferences(context)
                .getString(key,"list")!!
        }
    }
}