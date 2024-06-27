package es.upsa.mimo.android.laligaapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.upsa.mimo.android.laligaapp.R
import es.upsa.mimo.android.laligaapp.model.fixtures.FixtureResp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

internal class FixturesAdapter (
    private val fixturesList: List<FixtureResp>
    ) : RecyclerView.Adapter<FixturesAdapter.FixtureViewHolder>(){
    class FixtureViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val  homeTeam : TextView = itemView.findViewById(R.id.homeTeam)
        val homeTeamBadge : ImageView = itemView.findViewById(R.id.homeTeamBadge)
        val result : TextView = itemView.findViewById(R.id.result)
        val awayTeam : TextView = itemView.findViewById(R.id.awayTeam)
        val awayTeamBadge : ImageView = itemView.findViewById(R.id.awayTeamBadge)
        val date : TextView = itemView.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixtureViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fixture_item, parent, false) // Replace with your item layout
        return FixtureViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return fixturesList.size
    }

    override fun onBindViewHolder(holder: FixtureViewHolder, position: Int) {
        val fixture = fixturesList[position]
        holder.homeTeam.text = fixture.teams?.home?.name
        holder.awayTeam.text = fixture.teams?.away?.name
        holder.result.text = fixture.goals?.home.toString() + " - " + fixture.goals?.away.toString()
        holder.date.text = trimDate(fixture.fixture?.date)

        Glide.with(holder.itemView).load(fixture.teams?.home?.logo).into(holder.homeTeamBadge)
        Glide.with(holder.itemView).load(fixture.teams?.away?.logo).into(holder.awayTeamBadge)
    }

    fun trimDate(date: String?) : String {
        if (date != null) {
            return date.split("T")[0]
        }

        return ""
    }

}