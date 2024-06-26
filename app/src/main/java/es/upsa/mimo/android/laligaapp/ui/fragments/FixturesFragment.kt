package es.upsa.mimo.android.laligaapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import es.upsa.mimo.android.laligaapp.R
import es.upsa.mimo.android.laligaapp.network.ApiClient
import es.upsa.mimo.android.laligaapp.network.Status
import es.upsa.mimo.android.laligaapp.viewmodel.FixturesViewModel
import kotlinx.coroutines.launch

class FixturesFragment : Fragment(R.layout.fragment_fixtures){

    private lateinit var viewModel: FixturesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fixtures, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FixturesViewModel::class.java)

        viewModel.getFixtures(140, 2023)
        lifecycleScope.launch {

            viewModel.fixturesState.collect{
                when(it.status){
                    Status.LOADING -> {
                        Log.d("FixturesFragment", "Loading")
                    }
                    Status.SUCCESS -> {
                        Log.d("FixturesFragment", "Success")
                        it.data?.fixtureResp?.forEach { resp -> Log.d("FixturesFragment", "Fixture data: ${resp}")}
                    }
                    Status.ERROR -> {
                        Log.d("FixturesFragment", "Error")
                    }
                }
            }

        }
    }
}