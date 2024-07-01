package es.upsa.mimo.android.laligaapp.model.players

import com.google.gson.annotations.SerializedName


data class Goals (

  @SerializedName("total"    ) var total    : Int?    = null,
  @SerializedName("conceded" ) var conceded : Int?    = null,
  @SerializedName("assists"  ) var assists  : String? = null,
  @SerializedName("saves"    ) var saves    : String? = null

)