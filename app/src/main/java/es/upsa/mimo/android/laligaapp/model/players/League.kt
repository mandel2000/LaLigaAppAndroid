package es.upsa.mimo.android.laligaapp.model.players

import com.google.gson.annotations.SerializedName


data class League (

  @SerializedName("id"      ) var id      : Int?    = null,
  @SerializedName("name"    ) var name    : String? = null,
  @SerializedName("country" ) var country : String? = null,
  @SerializedName("logo"    ) var logo    : String? = null,
  @SerializedName("flag"    ) var flag    : String? = null,
  @SerializedName("season"  ) var season  : Int?    = null

)