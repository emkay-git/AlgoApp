package com.example.algoapp.ui.designPattern

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.example.algoapp.R
import com.example.algoapp.viewmodels.DesignPatternViewModel
import kotlinx.android.synthetic.main.fragment_design_pattern_desc.*


/**
 * A simple [Fragment] subclass.
 * Use the [DesignPatternDesc.newInstance] factory method to
 * create an instance of this fragment.
 */
class DesignPatternDesc : Fragment() {

    lateinit var designPatternViewModel: DesignPatternViewModel;
    lateinit var designPatternNavController: NavController;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_design_pattern_desc, container, false)
        // Inflate the layout for this fragment
        designPatternViewModel = ViewModelProviders.of(requireActivity()).get(DesignPatternViewModel::class.java);
        designPatternViewModel.selectedPattern.observe(viewLifecycleOwner, Observer {
            println(it.toString())
            dPNameText.text = it.name
            Glide.with(requireContext())
                .load(it.imageUrl)
                .into(dpImageView)
        })

        return view;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DesignPatternDesc.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DesignPatternDesc()

    }
}