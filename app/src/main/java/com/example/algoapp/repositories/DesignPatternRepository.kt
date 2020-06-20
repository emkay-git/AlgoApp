package com.example.algoapp.repositories

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.algoapp.models.DesignPattern
import com.example.algoapp.utils.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class DesignPatternRepository(app: Application) {

    private val listType = Types.newParameterizedType(
        List::class.java, DesignPattern::class.java
    );

    val designPatternList = MutableLiveData<List<DesignPattern>>();


    init {
        designPatternList.value  = parseData(FileHelper.getTextFromAssets(app,"designPatternData.json"))
    }

    private fun parseData(text: String) : List<DesignPattern> {
        val moshi  = Moshi.Builder().build();
        val adapter: JsonAdapter<List<DesignPattern>> = moshi.adapter(listType);
        val data = adapter.fromJson(text);
        return data?: emptyList();
    }
}