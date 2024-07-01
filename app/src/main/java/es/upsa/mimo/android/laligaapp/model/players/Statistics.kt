package es.upsa.mimo.android.laligaapp.model.players

import com.google.gson.annotations.SerializedName


data class Statistics (

  @SerializedName("team"        ) var team        : Team?        = Team(),
  @SerializedName("league"      ) var league      : League?      = League(),
  @SerializedName("games"       ) var games       : Games?       = Games(),
  @SerializedName("substitutes" ) var substitutes : Substitutes? = Substitutes(),
  @SerializedName("shots"       ) var shots       : Shots?       = Shots(),
  @SerializedName("goals"       ) var goals       : Goals?       = Goals(),
  @SerializedName("passes"      ) var passes      : Passes?      = Passes(),
  @SerializedName("tackles"     ) var tackles     : Tackles?     = Tackles(),
  @SerializedName("duels"       ) var duels       : Duels?       = Duels(),
  @SerializedName("dribbles"    ) var dribbles    : Dribbles?    = Dribbles(),
  @SerializedName("fouls"       ) var fouls       : Fouls?       = Fouls(),
  @SerializedName("cards"       ) var cards       : Cards?       = Cards(),
  @SerializedName("penalty"     ) var penalty     : Penalty?     = Penalty()

)