package com.example.algoapp.utils

import android.app.Application
import android.content.Context
import java.io.File

class FileHelper {
    companion object {

        fun getTextFromAssets(context: Context, fileName: String): String {
            return context.assets.open(fileName).use {
                it.bufferedReader().use {
                    it.readText()
                }
            }
        }


        fun saveTextToFile(app: Application,data: String?) {
            val file = File(app.filesDir,"algoList.json")
            file.writeText(data?:"", Charsets.UTF_8);
        }

        fun readTextFromFile(app: Application): String? {
            val file = File(app.cacheDir,"algoList.json")
            return if(file.exists()) file.readText() else null;
        }
    }
}