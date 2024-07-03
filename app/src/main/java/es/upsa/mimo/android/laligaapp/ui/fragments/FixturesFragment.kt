package es.upsa.mimo.android.laligaapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import es.upsa.mimo.android.laligaapp.R
import es.upsa.mimo.android.laligaapp.adapters.FixturesAdapter
import es.upsa.mimo.android.laligaapp.network.Status
import es.upsa.mimo.android.laligaapp.ui.decoration.DividerItemDecoration
import es.upsa.mimo.android.laligaapp.viewmodel.FixturesViewModel
import kotlinx.coroutines.launch

class FixturesFragment : Fragment(R.layout.fragment_fixtures){

    private lateinit var viewModel: FixturesViewModel
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var fixturesListView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fixtures, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fixturesListView = view.findViewById(R.id.fixturesList)
        fixturesListView.layoutManager = LinearLayoutManager(requireContext())
        fixturesListView.addItemDecoration(DividerItemDecoration())

        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh)
        swipeRefreshLayout.setOnRefreshListener { fetchDataAndRefreshUI() }

        viewModel = ViewModelProvider(this)[FixturesViewModel::class.java]

        fetchDataAndRefreshUI();


    }

    private fun fetchDataAndRefreshUI() {
        viewModel.getFixtures(140, 2023)

        lifecycleScope.launch {

            viewModel.fixturesState.collect {
                when (it.status) {
                    Status.LOADING -> {
                        Log.d("FixturesFragment", "Loading")
                    }

                    Status.SUCCESS -> {
                        Log.d("FixturesFragment", "Success")
                        it.data?.let { fixturesResponse ->
                            val fixturesData = fixturesResponse.fixtureResp

                            val fixturesAdapter = FixturesAdapter(fixturesList = fixturesData)
                            fixturesListView.adapter = fixturesAdapter
                        }
                    }

                    Status.ERROR -> {
                        Log.d("FixturesFragment", "Error")
                    }
                }
            }
        }

        swipeRefreshLayout.isRefreshing = false
    }
}