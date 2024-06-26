package es.upsa.mimo.android.laligaapp.ui.teams

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import es.upsa.mimo.android.laligaapp.R
import es.upsa.mimo.android.laligaapp.adapters.TeamsGridAdapter
import es.upsa.mimo.android.laligaapp.model.teams.Response
import es.upsa.mimo.android.laligaapp.network.ApiClient
import kotlinx.coroutines.launch

class TeamsFragment : Fragment(R.layout.fragment_teams){

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

        lifecycleScope.launch {
            val response = ApiClient.apiService.getTeams(league = 140, season = 2023)
            var teamData = ArrayList<Response>()
            if (response.isSuccessful) {
                teamData = response.body()?.response!!
                teamData.forEach { resp -> Log.d("TeamsFragment", "Team data: ${resp.team}")}

            } else {
                // Handle API error
                Log.e("TeamsFragment", "Error: ${response.message()}")
            }

            val courseAdapter = TeamsGridAdapter(courseList = teamData, requireContext())

            // on below line we are setting adapter to our grid view.
            teamsGridView.adapter = courseAdapter

            // on below line we are adding on item
            // click listener for our grid view.
            teamsGridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                // inside on click method we are simply displaying
                // a toast message with course name.
                Toast.makeText(
                    context, teamData[position].team?.name + " selected",
                    Toast.LENGTH_SHORT
                ).show()
            }


        }
    }



}