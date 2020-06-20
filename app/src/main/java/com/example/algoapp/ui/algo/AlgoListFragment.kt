package com.example.algoapp.ui.algo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.algoapp.R
import com.example.algoapp.ui.algo.adapter.AlgoListAdapter
import com.example.algoapp.viewmodels.AlgorithmViewModel
import com.example.algoapp.models.Algo

// TODO: Rename parameter arguments, choose names that match

/**
 * A simple [Fragment] subclass.
 * Use the [AlgoListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlgoListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var algorithmViewModel: AlgorithmViewModel;
    private lateinit var algoListRecylerView: RecyclerView;

    private lateinit var algoListAdapter: AlgoListAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        algorithmViewModel = ViewModelProviders.of(this).get(AlgorithmViewModel::class.java);

        hideUpButton()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_algo_list, container, false)
    }

    fun hideUpButton() {
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false);
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        algoListAdapter = AlgoListAdapter(emptyArray()) {
                algorithm, type ->  listClickEvents(type, algorithm)
        }
        algoListRecylerView = view.findViewById<RecyclerView>(R.id.reclyer_algo_list);
        setAlgoListRecyclerView(emptyArray(),algoListAdapter)
        algorithmViewModel.algorithmList.observe(viewLifecycleOwner, Observer {
                algoListAdapter.algoList = it;
                algoListAdapter.notifyDataSetChanged()
        });
    }

    private fun setAlgoListRecyclerView(data: Array<Algo>, algoListAdapter: AlgoListAdapter) {
        val linearLayout: RecyclerView.LayoutManager = LinearLayoutManager(activity);
        algoListRecylerView.layoutManager = linearLayout;
        algoListRecylerView.adapter  = algoListAdapter;
    }

    fun listClickEvents(type:String, data: Algo) {
        if(type.equals("learn"))
        {
            var bundle = Bundle();
            bundle.putSerializable(AlgoConstants.ALGO_OBJECT,data);
            findNavController().navigate(R.id.algoDetailsFragment,bundle);
        }
        else {
            findNavController().navigate(R.id.DFSFragment);
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment AlgoListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = AlgoListFragment();
    }
}