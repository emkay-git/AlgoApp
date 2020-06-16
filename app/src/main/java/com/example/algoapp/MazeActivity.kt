package com.example.algoapp

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.algoapp.utils.Constants
import com.example.algoapp.utils.MazeNode
import com.example.algoapp.viewmodels.MazeViewModel


class MazeActivity : AppCompatActivity() {

    private lateinit var mazeViewModel: MazeViewModel;
    private lateinit var tableLayout: TableLayout;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maze)

        tableLayout = findViewById(R.id.tableLayout);
        mazeViewModel = ViewModelProviders.of(this).get(MazeViewModel::class.java);

        mazeViewModel.randomMaze.observe(this, Observer { it
            createGridLayout(tableLayout,it);
         });

        val generateRandomMazeButton: Button = findViewById(R.id.genrate_random);
        generateRandomMazeButton.text = getString(R.string.random_maze_button);
        generateRandomMazeButton.setOnClickListener({ mazeViewModel.generateRandomMaze(); });


        val solveMazeButton: Button = findViewById(R.id.solve_maze);
        solveMazeButton.text = getString(R.string.maze_solve_button);
        solveMazeButton.setOnClickListener({mazeViewModel.solveMaze()});

        mazeViewModel.visitedNode.observe(this, Observer {
            changeNodeColor(it);
        });

    }

    private fun changeNodeColor(node: IntArray) {
        val table: TableRow = tableLayout.getChildAt(node[0]) as TableRow;
        var tableColumn: TextView = table.getChildAt(node[1]) as TextView;
        tableColumn = customView(tableColumn,Color.BLUE,10);
    }

    private fun createGridLayout(tableLayout: TableLayout,mazeNodes: Array<IntArray>) {
        tableLayout.removeAllViews();
        for(row in 0 until Constants.ROW) {
            val tableRow : TableRow = TableRow(this);
            for(col in 0 until Constants.COLUMN) {
                tableLayout.setColumnStretchable(col,true);
                var textView: TextView =  TextView(this);
                textView.gravity = Gravity.CENTER;
                textView.minHeight = 100;
                if(mazeNodes[row][col]==MazeNode.GROUND.ordinal)
                textView = customView(textView,Color.RED,10);
                else textView = customView(textView,Color.GRAY,10);
                tableRow.addView(textView);
            }
            tableRow.minimumHeight = 50;

            tableLayout.addView(tableRow);
        }
    }

    fun customView(v: TextView, backgroundColor: Int, borderColor: Int): TextView {
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.cornerRadii = floatArrayOf(8f, 8f, 8f, 8f, 0f, 0f, 0f, 0f)
        shape.setColor(backgroundColor)
        shape.setStroke(3, borderColor)
        v.background = shape
        return v;
    }
}