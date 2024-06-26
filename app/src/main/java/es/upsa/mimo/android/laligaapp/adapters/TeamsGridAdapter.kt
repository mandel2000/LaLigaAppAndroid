package es.upsa.mimo.android.laligaapp.adapters
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import es.upsa.mimo.android.laligaapp.R
import es.upsa.mimo.android.laligaapp.model.teams.TeamsResp

internal class TeamsGridAdapter(
        // on below line we are creating two
        // variables for course list and context
    private val teamList: List<TeamsResp>,
    private val context: Context
    ) :
        BaseAdapter() {
        // in base adapter class we are creating variables
        // for layout inflater, course image view and course text view.
        private var layoutInflater: LayoutInflater? = null
        private lateinit var teamTV: TextView
        private lateinit var teamIV: ImageView

        // below method is use to return the count of course list
        override fun getCount(): Int {
            return teamList.size
        }

        // below function is use to return the item of grid view.
        override fun getItem(position: Int): Any {
            return teamList[position]
        }

        // below function is use to return item id of grid view.
        override fun getItemId(position: Int): Long {
            return teamList[position].team?.id?.toLong()!!
        }

        // in below function we are getting individual item of grid view.
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            var convertView = convertView
            // on blow line we are checking if layout inflater
            // is null, if it is null we are initializing it.
            if (layoutInflater == null) {
                layoutInflater =
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            }
            // on the below line we are checking if convert view is null.
            // If it is null we are initializing it.
            if (convertView == null) {
                // on below line we are passing the layout file
                // which we have to inflate for each item of grid view.
                convertView = layoutInflater!!.inflate(R.layout.team_card, null)
            }
            // on below line we are initializing our course image view
            // and course text view with their ids.
            teamIV = convertView!!.findViewById(R.id.idIVCourse)
            teamTV = convertView.findViewById(R.id.idTVCourse)
            // on below line we are setting image for our course image view.
            Glide.with(convertView).load(teamList[position].team?.logo).into(teamIV)
            // on below line we are setting text in our course text view.
            teamTV.text = teamList[position].team?.name
            // at last we are returning our convert view.
            return convertView
        }
    }

