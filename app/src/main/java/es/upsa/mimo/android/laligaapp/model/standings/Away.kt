package es.upsa.mimo.android.laligaapp.model.standings

import com.google.gson.annotations.SerializedName


data class Away (

  @SerializedName("draw"   ) var draw   : Int?   = null,
  @SerializedName("goals"  ) var goals  : Goals? = Goals(),
  @SerializedName("lose"   ) var lose   : Int?   = null,
  @SerializedName("played" ) var played : Int?   = null,
  @SerializedName("win"    ) var win    : Int?   = null

)