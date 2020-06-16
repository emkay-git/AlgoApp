package com.example.algoapp.utils

import kotlin.random.Random

class RandomMaze {
    companion object {


        private fun getNodeType(): MazeNode {
           val isWall: Boolean =  Random.nextBoolean();
           if(isWall) return MazeNode.WALL;
            return MazeNode.GROUND;
        }


        fun createRandomMaze(): Array<IntArray> {

            var randomArray = Array(Constants.ROW) {IntArray(Constants.COLUMN)};

            for(row in 0 until Constants.ROW) {
                for(col in 0 until Constants.COLUMN) {
                    if(row==0&&col==0) randomArray[0][0] = MazeNode.GROUND.ordinal;
                    else if(row==Constants.ROW-1&&col==Constants.COLUMN-1)  randomArray[row][col] = MazeNode.GROUND.ordinal;
                    else randomArray[row][col] = getNodeType().ordinal;
                    print(randomArray[row][col].toString()+" ");
                }
               println();
            }


            return randomArray;
        }
    }

}