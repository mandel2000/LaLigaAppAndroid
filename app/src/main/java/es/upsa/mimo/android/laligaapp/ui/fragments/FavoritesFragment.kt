package es.upsa.mimo.android.laligaapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.upsa.mimo.android.laligaapp.BuildConfig
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import es.upsa.mimo.android.laligaapp.R
import es.upsa.mimo.android.laligaapp.adapters.FavTeamsGridAdapter
import es.upsa.mimo.android.laligaapp.adapters.OnItemClickListener
import es.upsa.mimo.android.laligaapp.viewmodel.SharedViewModel
import kotlinx.coroutines.launch

class FavoritesFragment : Fragment(R.layout.fragment_favorites){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favTeamsGridView : GridView = view.findViewById(R.id.teamFavoritesGridView)
        val favTeamsTextView : TextView = view.findViewById(R.id.favoritesTextView)

        if(BuildConfig.FLAVOR == "free"){
            favTeamsGridView.visibility = View.GONE
            favTeamsTextView.visibility = View.VISIBLE
        }else{

            favTeamsGridView.visibility = View.VISIBLE
            favTeamsTextView.visibility = View.GONE

            val sharedViewModel: SharedViewModel by activityViewModels()
            sharedViewModel.database.teamDao().let { teamDao ->
                lifecycleScope.launch {
                    val teams = teamDao.getAllTeams()
                    teams.collect { teamList ->
                        val mutableTeamList = teamList.toMutableList()
                        val favTeamsAdapter =
                            FavTeamsGridAdapter(teamList = mutableTeamList, requireContext(),
                                object : OnItemClickListener {
                                    override fun onFavButtonClicked(position: Int, id: Int) {
                                        lifecycleScope.launch {

                                            teamDao.deleteTeam(id)
                                            mutableTeamList.removeAt(position)
                                            (favTeamsGridView.adapter as? FavTeamsGridAdapter)?.notifyDataSetChanged()
                                        }
                                    }

                                })
                        favTeamsGridView.adapter = favTeamsAdapter

                    }

                }
            }
        }
    }

}