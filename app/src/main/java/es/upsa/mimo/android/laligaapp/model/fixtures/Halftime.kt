package es.upsa.mimo.android.laligaapp.model.fixtures

import com.google.gson.annotations.SerializedName


data class Halftime (

  @SerializedName("away" ) var away : Int? = null,
  @SerializedName("home" ) var home : Int? = null

)