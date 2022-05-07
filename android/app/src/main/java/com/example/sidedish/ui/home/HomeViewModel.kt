package com.example.sidedish.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sidedish.data.repository.AuthRepository
import com.example.sidedish.ui.common.SingleLiveEvent
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _jwtLoadCompleteEvent = SingleLiveEvent<Nothing>()
    val jwtLoadCompleteEvent: LiveData<Nothing> get() = _jwtLoadCompleteEvent

    private val _error = SingleLiveEvent<String>()
    val error: LiveData<String> get() = _error

    private val ceh = CoroutineExceptionHandler { _, _ ->
        _error.value = "네트워크 연결 실패"
    }

    fun loadJwt(authenticationCode: String) {
        viewModelScope.launch(ceh) {
            authRepository.loadJwt(authenticationCode)
            _jwtLoadCompleteEvent.call()
        }
    }
}