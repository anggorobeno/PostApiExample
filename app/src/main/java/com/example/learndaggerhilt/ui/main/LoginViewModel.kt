package com.example.learndaggerhilt.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learndaggerhilt.data.MainRepository
import com.example.learndaggerhilt.data.model.*
import com.example.learndaggerhilt.session.SessionManager
import com.example.learndaggerhilt.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _users = MutableLiveData<Int>()
    val users: MutableLiveData<Int>
        get() = _users

    fun postLogin(request: LoginRequest) {
        viewModelScope.launch {
            mainRepository.postLogin(request).let {
                if (it.isSuccessful && it.code() == 200) {
                    _users.postValue(it.body()!!.code)
                    sessionManager.saveAuthToken(it.body()!!.data.token)
                }
            }


        }

    }


}