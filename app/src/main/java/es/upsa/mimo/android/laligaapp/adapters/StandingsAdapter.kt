package es.upsa.mimo.android.laligaapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.upsa.mimo.android.laligaapp.R
import es.upsa.mimo.android.laligaapp.model.standings.Standings

class StandingsAdapter (private val dataList: List<Standings>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val HEADER = 0
        private const val ROW = 1
    }

    class StandingsHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
    class StandingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val team: TextView = itemView.findViewById(R.id.teamTextView)
        val played: TextView = itemView.findViewById(R.id.playedTextView)
        val victories: TextView = itemView.findViewById(R.id.victoriesTextView)
        val draws: TextView = itemView.findViewById(R.id.drawsTextView)
        val loses: TextView = itemView.findViewById(R.id.losesTextView)
        val gf: TextView = itemView.findViewById(R.id.gfTextView)
        val ga: TextView = itemView.findViewById(R.id.gaTextView)
        val gd: TextView = itemView.findViewById(R.id.gdTextView)
        val points: TextView = itemView.findViewById(R.id.pointsTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            HEADER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.standings_headers, parent, false)
                StandingsHeaderViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.standings_row, parent, false)
                StandingsViewHolder(view)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ROW -> {

                val data = dataList[position-1]
                holder as StandingsViewHolder
                holder.team.text = data.team?.name
                holder.played.text = data.all?.played.toString()
                holder.victories.text = data.all?.win.toString()
                holder.draws.text = data.all?.draw.toString()
                holder.loses.text = data.all?.lose.toString()
                holder.gf.text = data.all?.goals?.goalsFor.toString()
                holder.ga.text = data.all?.goals?.goalsAgainst.toString()
                holder.gd.text = data.goalsDiff.toString()
                holder.points.text = data.points.toString()
            }
            else -> {
                // Do nothing
            }
            }
    }

    override fun getItemCount(): Int {
        return dataList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) HEADER else ROW
    }
}