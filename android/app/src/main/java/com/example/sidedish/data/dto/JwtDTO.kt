package com.example.sidedish.data.dto


import com.example.sidedish.data.model.Jwt
import com.google.gson.annotations.SerializedName

data class JwtDTO(
    @SerializedName("accessToken")
    val accessToken: String?,
    @SerializedName("tokenType")
    val tokenType: String?
)

fun JwtDTO.toJwt() = Jwt(
    accessToken = requireNotNull(accessToken),
    tokenType = requireNotNull(tokenType)
)
