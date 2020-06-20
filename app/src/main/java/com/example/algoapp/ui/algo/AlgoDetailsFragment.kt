package com.example.algoapp.ui.algo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.algoapp.R
import com.example.algoapp.models.Algo

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val DFS_UL = "https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/"
/**
 * A simple [Fragment] subclass.
 * Use the [AlgoDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlgoDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var algoObject: Algo;
    private lateinit var navController: NavController;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            algoObject = it.getSerializable(AlgoConstants.ALGO_OBJECT)!! as Algo;
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_algo_details, container, false)

        navController = Navigation.findNavController(
            requireActivity(),R.id.algo_nav_host_fragment
        );
        setUpIcon();

        fillTheViewWebView(view,algoObject.descriptionUrl);
        return view;
    }

    fun setUpIcon() {
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(true);
        }
        setHasOptionsMenu(true);
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun fillTheViewWebView(view: View,url:String) {
        val webView: WebView = view.findViewById(R.id.algo_details_web_view);
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
        println(algoObject.toString());
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == android.R.id.home) {

            navController.navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment AlgoDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = AlgoDetailsFragment()
    }
}