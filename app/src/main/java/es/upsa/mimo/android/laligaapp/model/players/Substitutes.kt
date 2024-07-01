package es.upsa.mimo.android.laligaapp.model.players

import com.google.gson.annotations.SerializedName


data class Substitutes (

  @SerializedName("in"    ) var inValue    : Int? = null,
  @SerializedName("out"   ) var out   : Int? = null,
  @SerializedName("bench" ) var bench : Int? = null

)