package com.example.sidedish.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sidedish.common.SingleLiveEvent
import com.example.sidedish.data.OrderMenu
import com.example.sidedish.data.repository.MenuRepository
import com.example.sidedish.model.MenuDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuDetailViewModel @Inject constructor(
    private val menuRepository: MenuRepository
) : ViewModel() {

    private val _error = SingleLiveEvent<String>()
    val error: LiveData<String> get() = _error

    private val _count = MutableLiveData(0)
    val count: LiveData<Int> get() = _count

    private val _totalPrice = MutableLiveData(0)
    val totalPrice: LiveData<Int> get() = _totalPrice

    private val _menuOrderCompleteEvent = SingleLiveEvent<Nothing>()
    val menuOrderCompleteEvent: LiveData<Nothing> get() = _menuOrderCompleteEvent

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
        val count = _count.value ?: return
        val menuDetail = _menuDetail.value ?: return

        viewModelScope.launch(ceh) {
            val orderMenu = OrderMenu(menuDetail.id, count)
            menuRepository.orderMenu(orderMenu)
            _menuOrderCompleteEvent.call()
        }
    }

    fun addCount() {
        val count = _count.value ?: return
        _count.value = count + 1
        calculateTotalAmount()
    }

    fun subtractCount() {
        val count = _count.value ?: return
        if (count > 0) {
            _count.value = count - 1
        }
        calculateTotalAmount()
    }

    private fun calculateTotalAmount() {
        val count = _count.value ?: 0
        val price = _menuDetail.value?.price ?: 0

        if (_count.value == 0) {
            _totalPrice.value = 0
        } else {
            _totalPrice.value = price * count
        }
    }
}