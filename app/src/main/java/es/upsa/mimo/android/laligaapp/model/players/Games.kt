package es.upsa.mimo.android.laligaapp.model.players

import com.google.gson.annotations.SerializedName


data class Games (

  @SerializedName("appearences" ) var appearences : Int?     = null,
  @SerializedName("lineups"     ) var lineups     : Int?     = null,
  @SerializedName("minutes"     ) var minutes     : Int?     = null,
  @SerializedName("number"      ) var number      : String?  = null,
  @SerializedName("position"    ) var position    : String?  = null,
  @SerializedName("rating"      ) var rating      : String?  = null,
  @SerializedName("captain"     ) var captain     : Boolean? = null

)