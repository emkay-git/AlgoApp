package com.example.algoapp.ui.designPattern

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.algoapp.R
import com.example.algoapp.models.DesignPattern
import com.example.algoapp.utils.AlgoAppPreferences

class DesignPatternAdapter(val context: Context,
                           val data: List<DesignPattern>,
                           val itemClickListner: DesignPatternItemClickListner) : RecyclerView.Adapter<DesignPatternAdapter.DesignPatternViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesignPatternViewHolder {
            val inflator = LayoutInflater.from(parent.context);
            val layoutType = AlgoAppPreferences.getPreferences(context,AlgoAppPreferences.LAYOUT_TYPE);
        var view: View;
        if(layoutType.equals("list"))
             view  = inflator.inflate(R.layout.design_pattern_item_list,parent,false);
        else view = inflator.inflate(R.layout.design_pattern_item,parent,false);
            return DesignPatternViewHolder(view);
    }

    override fun getItemCount() = data.size;


    override fun onBindViewHolder(holder: DesignPatternViewHolder, position: Int) {
          holder.designPatternText.text = data.get(position).name;
          holder.itemView.setOnClickListener{
              itemClickListner.onClick(data.get(position))
          }

          Glide.with(context)
              .load(data.get(position).imageUrl)
              .into(holder.designPatternImage);
    }


    inner class DesignPatternViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        lateinit var designPatternText: TextView;
        lateinit var designPatternImage: ImageView;
            init {
                designPatternText = itemView.findViewById(R.id.dPNameText);
                designPatternImage = itemView.findViewById(R.id.dpImageView);
            }
    }

    interface DesignPatternItemClickListner {
        fun onClick(designPattern: DesignPattern);
    }
}