package es.upsa.mimo.android.laligaapp.model.standings

import com.google.gson.annotations.SerializedName


data class Response (

  @SerializedName("league" ) var league : League? = League()

)