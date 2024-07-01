package es.upsa.mimo.android.laligaapp.model.players

import com.google.gson.annotations.SerializedName


data class Cards (

  @SerializedName("yellow"    ) var yellow    : Int? = null,
  @SerializedName("yellowred" ) var yellowred : Int? = null,
  @SerializedName("red"       ) var red       : Int? = null

)