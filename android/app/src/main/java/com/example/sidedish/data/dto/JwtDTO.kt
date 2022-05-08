package com.example.sidedish.data.dto


import com.example.sidedish.model.Jwt

data class JwtDTO(
    val accessToken: String?,
    val tokenType: String?
)

fun JwtDTO.toJwt() = Jwt(
    accessToken = requireNotNull(accessToken),
    tokenType = requireNotNull(tokenType)
)