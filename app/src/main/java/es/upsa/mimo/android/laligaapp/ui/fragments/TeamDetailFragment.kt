package es.upsa.mimo.android.laligaapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import es.upsa.mimo.android.laligaapp.R
import es.upsa.mimo.android.laligaapp.network.Status
import es.upsa.mimo.android.laligaapp.viewmodel.TeamsViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TeamDetailFragment : Fragment(R.layout.fragment_team_detail){

    private val args: TeamDetailFragmentArgs by navArgs()

    private lateinit var viewModel: TeamsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemId = args.teamId

        viewModel = ViewModelProvider(this)[TeamsViewModel::class.java]

        viewModel.getTeamsDetails(140, itemId, 2023)

        val teamBadge : ImageView = view.findViewById(R.id.teamBadge)
        val teamName: TextView = view.findViewById(R.id.teamName)
        val city: TextView = view.findViewById(R.id.city)

        lifecycleScope.launch {
            viewModel.teamsState.collect{
                when(it.status){
                    Status.LOADING ->{
                        //TODO
                    }
                    Status.SUCCESS -> {
                        it.data?.let { teamsResponse ->
                            val teamDetails = teamsResponse.teamsResp[0];
                            teamName.text = teamDetails.team?.name
                            city.text = teamDetails.venue?.city + ", " + teamDetails.team?.country
                            Glide.with(view).load(teamDetails.team?.logo).into(teamBadge)

                            val starButton: ToggleButton = view.findViewById(R.id.favButton)
                            starButton.setOnCheckedChangeListener { _, isChecked ->
                                if (isChecked) {
                                    Toast.makeText(
                                        context, "Equipo " + teamDetails.team?.name + " añadido a favoritos",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        context, "Equipo " + teamDetails.team?.name + " eliminado de favoritos",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                        }
                    }
                    Status.ERROR ->{
                        //TODO
                    }

                }
            }
        }

    }
}