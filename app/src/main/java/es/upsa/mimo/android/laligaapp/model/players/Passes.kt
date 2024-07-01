package es.upsa.mimo.android.laligaapp.model.players

import com.google.gson.annotations.SerializedName


data class Passes (

  @SerializedName("total"    ) var total    : String? = null,
  @SerializedName("key"      ) var key      : String? = null,
  @SerializedName("accuracy" ) var accuracy : String? = null

)