package es.upsa.mimo.android.laligaapp.model.players

import com.google.gson.annotations.SerializedName


data class Penalty (

  @SerializedName("won"      ) var won      : String? = null,
  @SerializedName("commited" ) var commited : String? = null,
  @SerializedName("scored"   ) var scored   : Int?    = null,
  @SerializedName("missed"   ) var missed   : Int?    = null,
  @SerializedName("saved"    ) var saved    : String? = null

)