package com.example.algoapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.algoapp.services.DfsMazeSolver
import com.example.algoapp.services.MazeSolver
import com.example.algoapp.utils.RandomMaze

class MazeViewModel(app: Application): AndroidViewModel(app) {
    private val mazeSolver: MazeSolver = DfsMazeSolver();

    val randomMaze =  MutableLiveData<Array<IntArray>>();
    val visitedNode = MutableLiveData<IntArray>();
    init {

        mazeSolver.indexSubject.subscribe( {
            visitedNode.value = it;
        });
       generateRandomMaze();
    }

    fun solveMaze() {
        mazeSolver.solve(randomMaze.value!!, intArrayOf(0,0), intArrayOf(9,9));
    }

    fun generateRandomMaze() {
        randomMaze.value = RandomMaze.createRandomMaze();
    }


}