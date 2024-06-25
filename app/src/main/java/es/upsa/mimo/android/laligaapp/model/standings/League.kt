package es.upsa.mimo.android.laligaapp.model.standings

import com.google.gson.annotations.SerializedName


data class League (

  @SerializedName("country"   ) var country   : String?                         = null,
  @SerializedName("flag"      ) var flag      : String?                         = null,
  @SerializedName("id"        ) var id        : Int?                            = null,
  @SerializedName("logo"      ) var logo      : String?                         = null,
  @SerializedName("name"      ) var name      : String?                         = null,
  @SerializedName("season"    ) var season    : Int?                            = null,
  @SerializedName("standings" ) var standings : ArrayList<ArrayList<Standings>> = arrayListOf()

)