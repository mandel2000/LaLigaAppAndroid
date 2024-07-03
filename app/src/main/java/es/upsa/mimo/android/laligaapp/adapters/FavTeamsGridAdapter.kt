package es.upsa.mimo.android.laligaapp.adapters
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import es.upsa.mimo.android.laligaapp.R
import es.upsa.mimo.android.laligaapp.db.entities.TeamEntity
import es.upsa.mimo.android.laligaapp.model.teams.TeamsResp

internal class FavTeamsGridAdapter(

    private val teamList: MutableList<TeamEntity>,
    private val context: Context,
    private val onItemClickListener: OnItemClickListener
    ) :
        BaseAdapter() {

        private var layoutInflater: LayoutInflater? = null
        private lateinit var teamTV: TextView
        private lateinit var teamIV: ImageView
        private lateinit var teamButton : Button

        override fun getCount(): Int {
            return teamList.size
        }

        // below function is use to return the item of grid view.
        override fun getItem(position: Int): Any {
            return teamList[position]
        }

        // below function is use to return item id of grid view.
        override fun getItemId(position: Int): Long {
            return teamList[position].id.toLong()
        }

        // in below function we are getting individual item of grid view.
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            var convertView = convertView

            if (layoutInflater == null) {
                layoutInflater =
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            }

            if (convertView == null) {

                convertView = layoutInflater!!.inflate(R.layout.fav_team_card, null)
            }

            teamIV = convertView!!.findViewById(R.id.idIVFavTeam)
            teamTV = convertView.findViewById(R.id.idTVFavTeam)
            teamButton = convertView.findViewById(R.id.favButton)

            Glide.with(convertView).load(teamList[position].logo).into(teamIV)

            teamTV.text = teamList[position].name

            teamButton.setOnClickListener {
                onItemClickListener.onFavButtonClicked(position, teamList[position].id)
            }

            return convertView
        }

        fun removeItem(position: Int) {
            teamList.removeAt(position)
            notifyDataSetChanged()
        }
    }

    interface OnItemClickListener {
        fun onFavButtonClicked(position: Int, id: Int)
    }

