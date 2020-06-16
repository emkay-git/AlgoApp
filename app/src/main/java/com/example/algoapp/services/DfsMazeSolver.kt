package com.example.algoapp.services

import com.example.algoapp.utils.Constants
import com.example.algoapp.utils.MazeNode
import io.reactivex.subjects.BehaviorSubject

class DfsMazeSolver: MazeSolver {
    override var indexSubject: BehaviorSubject<IntArray>  =  BehaviorSubject.create<IntArray>();

    private lateinit var  visited : Array<BooleanArray>;
    override fun solve(maze: Array<IntArray>, origin: IntArray, destination: IntArray) {

       visited = Array(Constants.ROW) { BooleanArray(Constants.COLUMN) };
       solve(maze,origin[0],origin[1],destination);
    }

    private fun solve(maze: Array<IntArray>, i: Int, j: Int, destination: IntArray) {
        if(i>-1&&j>-1&&i<Constants.ROW&&j<Constants.COLUMN&&maze[i][j]!=MazeNode.WALL.ordinal&&!visited[i][j]) {
            visited[i][j] = true;

            indexSubject.onNext(intArrayOf(i,j));
            if(i==destination[0]&&j==destination[1]) {
//                indexSubject.onComplete();
                return;
            }

            for((dx,dy) in directions) {
                val a = i+dx;
                val b = j+dy;
                solve(maze,a,b,destination);
            }
        }
    }

}