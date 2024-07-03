package es.upsa.mimo.android.laligaapp.adapters

import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.upsa.mimo.android.laligaapp.R
import es.upsa.mimo.android.laligaapp.model.players.PlayerResp

internal class PlayerListAdapter (
    private val playersList : List<PlayerResp>
    ): RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder>() {

        class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view){
            val playerName : TextView = view.findViewById(R.id.playerName)
            val playerPhoto : ImageView = view.findViewById(R.id.playerPhoto)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_item, parent, false)
        return PlayerViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return playersList.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = playersList[position]
        holder.playerName.text = player.player?.name
        Glide.with(holder.itemView).load(player.player?.photo).into(holder.playerPhoto)

    }

}