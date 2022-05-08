package com.example.sidedish.data

import com.example.sidedish.model.Jwt
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppSession @Inject constructor() {
    var jwt: Jwt? = null
}