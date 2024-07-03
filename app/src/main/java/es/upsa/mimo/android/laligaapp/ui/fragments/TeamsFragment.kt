package es.upsa.mimo.android.laligaapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import es.upsa.mimo.android.laligaapp.R
import es.upsa.mimo.android.laligaapp.adapters.TeamsGridAdapter
import es.upsa.mimo.android.laligaapp.network.Status
import es.upsa.mimo.android.laligaapp.viewmodel.TeamsViewModel
import kotlinx.coroutines.launch

class TeamsFragment : Fragment(R.layout.fragment_teams){

    private lateinit var viewModel: TeamsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val teamsGridView : GridView = view.findViewById(R.id.teamsGridView)
        viewModel = ViewModelProvider(this)[TeamsViewModel::class.java]

        viewModel.getTeams(140, 2023)

        val navController = findNavController();

        lifecycleScope.launch {

            viewModel.teamsState.collect{
                when(it.status){
                    Status.LOADING -> {

                    }

                    Status.SUCCESS -> {
                        it.data?.let { teamResponse ->
                            val teamData = teamResponse.teamsResp

                            val teamsAdapter = TeamsGridAdapter(teamList = teamData, requireContext())

                            teamsGridView.adapter = teamsAdapter

                            teamsGridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->

                                val action = TeamsFragmentDirections.actionTeamsFragmentToTeamDetailFragment(teamData[position].team?.id!!)
                                findNavController().navigate(action)

                                Toast.makeText(
                                    context, teamData[position].team?.name + " selected",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        }
                    }

                    Status.ERROR -> {
                        Toast.makeText(context, "Error calling API", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("Navigation", "Current destination: ${findNavController().currentDestination?.id}")
    }




}