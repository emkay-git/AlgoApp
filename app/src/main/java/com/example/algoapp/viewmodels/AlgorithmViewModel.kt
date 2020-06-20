package com.example.algoapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.algoapp.ui.algo.respository.AlgoRepository

class AlgorithmViewModel(app: Application): AndroidViewModel(app) {
    var algorithmList =  AlgoRepository(app).algoList;
}

//arrayOf(
//Algorithm("1","Depth First Search","You search nodes based on the depth","https://en.wikipedia.org/wiki/Depth-first_search"),
//Algorithm("2","Breadth First Search","You visit nodes based on the width","https://en.wikipedia.org/wiki/Breadth-first_search"),
//Algorithm("3","Binary Search","Search which is better than linear","https://en.wikipedia.org/wiki/Binary_search_algorithm")
//);
