package com.example.algoapp.services

import io.reactivex.subjects.BehaviorSubject

interface MazeSolver {
    val directions: Array<IntArray>
        get() = arrayOf(intArrayOf(0,1), intArrayOf(-1,0), intArrayOf(1,0), intArrayOf(0,-1));

    val indexSubject: BehaviorSubject<IntArray>;
    fun solve(maze: Array<IntArray>,origin: IntArray, destination:IntArray);
}