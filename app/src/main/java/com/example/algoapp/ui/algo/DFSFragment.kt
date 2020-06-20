package com.example.algoapp.ui.algo

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.algoapp.R
import com.example.algoapp.utils.Constants
import com.example.algoapp.utils.MazeNode
import com.example.algoapp.viewmodels.MazeViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DFSFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DFSFragment : Fragment() {
    private lateinit var mazeViewModel: MazeViewModel;
    private lateinit var tableLayout: TableLayout;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_d_f_s, container, false)


        tableLayout = view.findViewById(R.id.tableLayout);
        mazeViewModel = ViewModelProviders.of(this).get(MazeViewModel::class.java);

        mazeViewModel.randomMaze.observe(viewLifecycleOwner, Observer { it
            createGridLayout(tableLayout,it);
        });

        val generateRandomMazeButton: Button = view.findViewById(R.id.genrate_random);
        generateRandomMazeButton.text = getString(R.string.random_maze_button);
        generateRandomMazeButton.setOnClickListener({ mazeViewModel.generateRandomMaze(); });


        val solveMazeButton: Button = view.findViewById(R.id.solve_maze);
        solveMazeButton.text = getString(R.string.maze_solve_button);
        solveMazeButton.setOnClickListener({mazeViewModel.solveMaze()});

        mazeViewModel.visitedNode.observe(viewLifecycleOwner, Observer {
            changeNodeColor(it);
        });

        return view;

    }

    private fun changeNodeColor(node: IntArray) {
        val table: TableRow = tableLayout.getChildAt(node[0]) as TableRow;
        var tableColumn: TextView = table.getChildAt(node[1]) as TextView;
        tableColumn = customView(tableColumn, Color.BLUE,10);
    }

    private fun createGridLayout(tableLayout: TableLayout, mazeNodes: Array<IntArray>) {
        tableLayout.removeAllViews();
        for(row in 0 until Constants.ROW) {
            val tableRow : TableRow = TableRow(activity);
            for(col in 0 until Constants.COLUMN) {
                tableLayout.setColumnStretchable(col,true);
                var textView: TextView =  TextView(activity);
                textView.gravity = Gravity.CENTER;
                textView.minHeight = 100;
                if(mazeNodes[row][col]== MazeNode.GROUND.ordinal)
                    textView = customView(textView, Color.RED,10);
                else textView = customView(textView, Color.GRAY,10);
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DFSFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            DFSFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}