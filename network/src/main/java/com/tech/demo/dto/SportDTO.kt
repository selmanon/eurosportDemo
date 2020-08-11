package com.tech.demo.dto

import com.google.gson.annotations.SerializedName

class SportDTO(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var SportName: String
)
