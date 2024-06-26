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
import es.upsa.mimo.android.laligaapp.viewmodel.StandingsViewModel
import kotlinx.coroutines.launch

class StandingsFragment : Fragment(R.layout.fragment_standings){

    private lateinit var viewModel: StandingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_standings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(StandingsViewModel::class.java)

        viewModel.getStandings(140, 2023)

        lifecycleScope.launch {

            viewModel.standingsState.collect{
                when(it.status){
                    Status.LOADING ->{
                        Log.d("StandingsFragment", "Loading")
                    }
                    Status.SUCCESS ->{
                        Log.d("StandingsFragment", "Success")
                        it.data?.standingsResp?.forEach { resp -> Log.d("StandingsFragment", "Standings data: ${resp}")}
                    }
                    Status.ERROR ->{
                        Log.d("StandingsFragment", "Error")
                    }
                }
            }

        }
    }
}