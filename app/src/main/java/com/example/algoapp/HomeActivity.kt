package com.example.algoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button

class HomeActivity : AppCompatActivity() {
    private lateinit var dfsButton: Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_main)

        dfsButton = findViewById(R.id.maze_dfs_button);
        dfsButton.text = "DFS MAZE SOLVER";
        dfsButton.setOnClickListener{
            val intent = Intent(this,MazeActivity::class.java)
            startActivity(intent);
        };

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
}