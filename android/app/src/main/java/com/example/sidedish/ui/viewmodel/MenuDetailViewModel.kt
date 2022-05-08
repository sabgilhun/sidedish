package com.example.sidedish.ui.viewmodel

import android.accounts.NetworkErrorException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sidedish.common.SingleLiveEvent
import com.example.sidedish.data.Menu
import com.example.sidedish.data.MenuModel
import com.example.sidedish.data.OrderMenu
import com.example.sidedish.data.repository.MenuRepository
import com.example.sidedish.model.MenuDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

private const val MAIN_MENU = 1
const val SOUP = 2
private const val SIDE_MENU = 3

@HiltViewModel
class MenuDetailViewModel @Inject constructor(
    private val menuRepository: MenuRepository
) : ViewModel() {
    private val _menu = MutableLiveData<List<MenuModel>>()
    val menu: LiveData<List<MenuModel>> = _menu

    private val _selectedFoodDetail = MutableLiveData<Menu>()
    val selectedFoodDetail: LiveData<Menu> get() = _selectedFoodDetail

    private val _error = SingleLiveEvent<String>()
    val error: LiveData<String> get() = _error

    private val _count = MutableLiveData<Int>()
    val count: LiveData<Int> get() = _count

    private val _price = MutableLiveData<Int>()
    val price: LiveData<Int> get() = _price

    private val _detailPrice = MutableLiveData<Int>()

    private val _discountRate = MutableLiveData<Int>()

    private lateinit var jwt: String

    private val ceh = CoroutineExceptionHandler { _, _ ->
        _error.value = "네트워크 연결 실패"
    }

    private val _menuDetail = MutableLiveData<MenuDetail>()
    val menuDetail: LiveData<MenuDetail> get() = _menuDetail

    fun loadMenuDetail(key: Int) {
        viewModelScope.launch(ceh) {
            val result = menuRepository.loadMenuDetail(key)
            _menuDetail.value = result
        }
    }

    fun orderMenu() {
        viewModelScope.launch() {
            if (_count.value == 0) {
                return@launch
            }
            val menu = OrderMenu(_selectedFoodDetail.value!!.id!!, _count.value!!)
//            repository.orderMenu(jwt, menu)
        }
    }

    fun addCount() {
        _count.value = (count.value)?.plus(1)
        calculateTotalAmount()
    }

    fun subtractCount() {
        if (count.value!! > 0) {
            _count.value = (count.value)?.minus(1)
        }
        calculateTotalAmount()
    }

    private fun calculateTotalAmount() {
        if (_count.value == 0) {
            _price.value = 0
        } else {
            _price.value = _detailPrice.value?.let { _count.value?.times(it) }
        }
    }

    fun pushBackCountToZero() {
        _count.value = 0
        _price.value = 0
    }
}