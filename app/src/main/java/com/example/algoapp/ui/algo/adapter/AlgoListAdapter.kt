package com.example.algoapp.ui.algo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.algoapp.R
import com.example.algoapp.models.Algo


/**
 * Adapter binds the data with the item of the view
 **/
class AlgoListAdapter(
    var algoList: Array<Algo>,
    private val clickListner: (Algo, type:String) -> Unit
): RecyclerView.Adapter<AlgoListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlgoListViewHolder {
        val inflater = LayoutInflater.from(parent.context);
        return AlgoListViewHolder(inflater, parent);
    }

    override fun getItemCount(): Int {
        return algoList.size;
    }

    override fun onBindViewHolder(holder: AlgoListViewHolder, position: Int) {

        holder.learnButton.setOnClickListener{clickListner(algoList[position],"learn")}
        holder.visualizeButton.setOnClickListener{clickListner(algoList[position],"visualize")}
       holder.bind(algoList[position]);
    }


}

// view holder is responsible for binding xml of item with the recycler view.
class AlgoListViewHolder(inflater: LayoutInflater,parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.algo_list_item, parent, false)){

    var algoTitleTextView: TextView? = null;
    var algoDescTextView: TextView? = null;
    var learnButton: Button;
    var visualizeButton: Button;
    var imageView: ImageView
    init {

        algoTitleTextView = itemView.findViewById(R.id.algo_list_item_text);
        algoDescTextView = itemView.findViewById(R.id.algo_list_item_desc);
        learnButton = itemView.findViewById(R.id.learn_button);
        visualizeButton = itemView.findViewById(R.id.visualize_button);
        imageView = itemView.findViewById(R.id.algoImageView)
    }

    fun bind(algo: Algo) {
        algoTitleTextView?.text = algo.name;
        algoDescTextView?.text = algo.description;
        Glide.with(itemView)
            .load(algo.algoImageUrl)
            .into(imageView)
    }

}