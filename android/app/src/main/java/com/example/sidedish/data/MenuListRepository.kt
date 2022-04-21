package com.example.sidedish.data

import android.util.Log

class MenuListRepository(private val dataSource: DataSource) {

    suspend fun getMainFoodList(): List<Body>? {
        val data = dataSource.getMainFoodList()

        return when(data.isSuccessful) {
            true -> data.body()?.body
            false -> throw RuntimeException("network fail")
        }
    }

    suspend fun getSoupFoodList(): List<Body>? {
        val data = dataSource.getSoupFoodList()
        return when(data.isSuccessful) {
            true -> data.body()?.body
            false -> throw RuntimeException("network fail")
        }
    }

    suspend fun getSideFoodList(): List<Body>? {
        val data = dataSource.getSideFoodList()
        return when(data.isSuccessful) {
            true -> data.body()?.body
            false -> throw RuntimeException("network fail")
        }
    }

    suspend fun getSelectedFoodDetail(hashId: String): Detail? {
        val data = dataSource.getFoodDetail(hashId)
        Log.d("MainDetail", "${data.body()?.data}")
        return when(data.isSuccessful) {
            true -> data.body()?.data
            false -> throw RuntimeException("Detail network fail")
        }
    }

}