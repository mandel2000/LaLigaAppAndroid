package es.upsa.mimo.android.laligaapp.model.fixtures

import com.google.gson.annotations.SerializedName


data class Fixture (

  @SerializedName("date"      ) var date      : String?  = null,
  @SerializedName("id"        ) var id        : Int?     = null,
  @SerializedName("periods"   ) var periods   : Periods? = Periods(),
  @SerializedName("referee"   ) var referee   : String?  = null,
  @SerializedName("status"    ) var status    : Status?  = Status(),
  @SerializedName("timestamp" ) var timestamp : Int?     = null,
  @SerializedName("timezone"  ) var timezone  : String?  = null,
  @SerializedName("venue"     ) var venue     : Venue?   = Venue()

)