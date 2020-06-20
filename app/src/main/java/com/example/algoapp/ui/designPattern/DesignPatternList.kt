package com.example.algoapp.ui.designPattern

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Adapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.algoapp.R
import com.example.algoapp.models.DesignPattern
import com.example.algoapp.utils.AlgoAppPreferences
import com.example.algoapp.viewmodels.DesignPatternViewModel
import kotlinx.android.synthetic.main.fragment_design_pattern_list.*


/**
 * A simple [Fragment] subclass.
 * Use the [DesignPatternList.newInstance] factory method to
 * create an instance of this fragment.
 */
class DesignPatternList : Fragment(), DesignPatternAdapter.DesignPatternItemClickListner{

    lateinit var designPatternRecyclerView: RecyclerView;
    lateinit var designPatternAdapter: DesignPatternAdapter;
    lateinit var designPatternViewModelProviders: DesignPatternViewModel;
    lateinit var designPatternNavController: NavController;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_design_pattern_list, container, false)

        designPatternViewModelProviders = ViewModelProviders.of(requireActivity()).get(DesignPatternViewModel::class.java);
        designPatternRecyclerView = view.findViewById(R.id.designPatternRecyclerView);
        designPatternNavController = Navigation.findNavController(requireActivity(),R.id.design_pattern_nav_host)
        designPatternViewModelProviders.designPatternList.observe(viewLifecycleOwner, Observer{
            designPatternAdapter = DesignPatternAdapter(requireActivity(), it,this);
            setRecyclerView(designPatternAdapter);
        });


//        setRecyclerView(designPatternAdapter);
        // this method calls the onCreateOptionsMenu() inside the fragment and there we can
        // give our own menu.

        setHasOptionsMenu(true);
        return view;
    }

    private fun setRecyclerView(adapter: DesignPatternAdapter) {
        var layoutType = AlgoAppPreferences.getPreferences(
            requireContext(),
            AlgoAppPreferences.LAYOUT_TYPE)
        println(layoutType);
        if(layoutType.equals("list")) {
            designPatternRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
            designPatternRecyclerView.adapter = adapter;
        } else {
            designPatternRecyclerView.layoutManager = GridLayoutManager(requireActivity(),2);
            designPatternRecyclerView.adapter = adapter;
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.design_pattern_menu,menu);
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        println(item.itemId)
        when(item.itemId) {
            R.id.action_view_grid -> {
                AlgoAppPreferences.setPreferences(requireContext(),AlgoAppPreferences.LAYOUT_TYPE,"grid")

            }
            R.id.action_view_list -> {
                AlgoAppPreferences.setPreferences(requireContext(),AlgoAppPreferences.LAYOUT_TYPE,"list")
            }
        }
        setRecyclerView(designPatternAdapter)
        return true;
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) = DesignPatternList()
    }

    override fun onClick(designPattern: DesignPattern) {
        designPatternViewModelProviders.selectedPattern.value = designPattern;
        designPatternNavController.navigate(R.id.designPatternDesc);

    }
}