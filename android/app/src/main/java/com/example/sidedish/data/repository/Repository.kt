package com.example.sidedish.data.repository

import com.example.sidedish.data.*
import com.example.sidedish.data.dto.inputDTOToMenu
import com.example.sidedish.data.datasource.DataSource
import com.example.sidedish.data.dto.toJwt
import retrofit2.Response
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val dataSource: DataSource) {

    suspend fun getSelectedFoodDetail(token: String, id: Int): Menu? {
        return dataSource.getFoodDetail(token, id).getBodyOrThrow()?.inputDTOToMenu()
    }

    suspend fun orderMenu(token: String, menu: OrderMenu): Boolean {
        return dataSource.orderMenu(token, menu).isSuccessful
    }

    private fun <T> Response<T>.getBodyOrThrow(): T? {
        return if (this.isSuccessful) this.body() else throw RuntimeException("network fail")
    }
}