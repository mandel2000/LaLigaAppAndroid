package es.upsa.mimo.android.laligaapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import es.upsa.mimo.android.laligaapp.R
import es.upsa.mimo.android.laligaapp.adapters.PlayerListAdapter
import es.upsa.mimo.android.laligaapp.viewmodel.PlayersViewModel
import es.upsa.mimo.android.laligaapp.viewmodel.TeamsViewModel

@AndroidEntryPoint

class TeamDetailFragment : Fragment(R.layout.fragment_team_detail) {

    private val args: TeamDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val teamId = args.teamId

        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)

        val adapter = TeamDetailPagerAdapter(this, teamId)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Team Info"
                1 -> "Players"
                else -> ""
            }
        }.attach()
    }

    inner class TeamDetailPagerAdapter(fragment: Fragment, private val teamId: Int) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {val fragment = TeamInfoFragment()
                        val bundle = Bundle()
                        bundle.putInt("teamId", teamId)
                        fragment.arguments = bundle
                        return fragment}
                1 -> {val fragment = PlayerListFragment()
                    val bundle = Bundle()
                    bundle.putInt("teamId", teamId)
                    fragment.arguments = bundle
                    return fragment
                }
                else -> throw IllegalStateException("Invalid position")
            }
        }
    }
}


