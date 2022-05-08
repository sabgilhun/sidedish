package com.example.sidedish.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sidedish.data.repository.MenuRepository
import com.example.sidedish.model.MenuCategory
import com.example.sidedish.model.MenuListItem
import com.example.sidedish.common.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val menuRepository: MenuRepository
) : ViewModel() {

    private val _menu = MutableLiveData<List<MenuListItem>>()
    val menu: LiveData<List<MenuListItem>> = _menu

    private val _error = SingleLiveEvent<String>()
    val error: LiveData<String> get() = _error

    private val ceh = CoroutineExceptionHandler { _, _ ->
        _error.value = "네트워크 연결 실패"
    }

    fun loadMenuList() {
        viewModelScope.launch(ceh) {
            val results = listOf(
                async { menuRepository.loadMenuList(MenuCategory.MAIN_MENU) },
                async { menuRepository.loadMenuList(MenuCategory.SOUP) },
                async { menuRepository.loadMenuList(MenuCategory.SIDE_MENU) }
            ).awaitAll()

            val totalMenuList = mutableListOf<MenuListItem>().apply {
                results.forEach { addAll(it) }
            }

            _menu.value = totalMenuList
        }
    }
}