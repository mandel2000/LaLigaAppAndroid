package es.upsa.mimo.android.laligaapp.model.fixtures

import com.google.gson.annotations.SerializedName


data class Penalty (

  @SerializedName("away" ) var away : String? = null,
  @SerializedName("home" ) var home : String? = null

)