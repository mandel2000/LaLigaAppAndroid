package es.upsa.mimo.android.laligaapp.model.standings

import com.google.gson.annotations.SerializedName


data class Goals (

  @SerializedName("against" ) var against : Int? = null,
  @SerializedName("for"     ) var for     : Int? = null

)