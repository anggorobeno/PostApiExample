package com.example.learndaggerhilt.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learndaggerhilt.data.MainRepository
import com.example.learndaggerhilt.data.model.LoginResponse
import com.example.learndaggerhilt.data.model.RegisterRequest
import com.example.learndaggerhilt.data.model.StatusResponse
import com.example.learndaggerhilt.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val sessionManager: SessionManager
) : ViewModel() {
    private val _users = MutableLiveData<String>()
    val users: MutableLiveData<String>
        get() = _users

    fun postRegister(request: RegisterRequest) {
        viewModelScope.launch {
            mainRepository.postRegister(request).let {
                if (it.isSuccessful && it.code() == 200) {
                    _users.postValue(it.message())
                }
            }


        }
    }


}