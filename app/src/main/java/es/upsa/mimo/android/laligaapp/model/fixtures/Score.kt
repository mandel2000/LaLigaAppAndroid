package es.upsa.mimo.android.laligaapp.model.fixtures

import com.google.gson.annotations.SerializedName


data class Score (

  @SerializedName("extratime" ) var extratime : Extratime? = Extratime(),
  @SerializedName("fulltime"  ) var fulltime  : Fulltime?  = Fulltime(),
  @SerializedName("halftime"  ) var halftime  : Halftime?  = Halftime(),
  @SerializedName("penalty"   ) var penalty   : Penalty?   = Penalty()

)