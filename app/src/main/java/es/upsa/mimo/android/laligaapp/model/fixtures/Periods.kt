package es.upsa.mimo.android.laligaapp.model.fixtures

import com.google.gson.annotations.SerializedName


data class Periods (

  @SerializedName("first"  ) var first  : Int? = null,
  @SerializedName("second" ) var second : Int? = null

)