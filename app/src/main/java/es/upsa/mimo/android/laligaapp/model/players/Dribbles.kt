package es.upsa.mimo.android.laligaapp.model.players

import com.google.gson.annotations.SerializedName


data class Dribbles (

  @SerializedName("attempts" ) var attempts : String? = null,
  @SerializedName("success"  ) var success  : String? = null,
  @SerializedName("past"     ) var past     : String? = null

)