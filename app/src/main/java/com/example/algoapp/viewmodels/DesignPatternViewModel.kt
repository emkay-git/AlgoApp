package com.example.algoapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.algoapp.models.DesignPattern
import com.example.algoapp.repositories.DesignPatternRepository

class DesignPatternViewModel(app: Application): AndroidViewModel(app) {
    val designPatternList = DesignPatternRepository(app).designPatternList;

    val selectedPattern = MutableLiveData<DesignPattern>();
}