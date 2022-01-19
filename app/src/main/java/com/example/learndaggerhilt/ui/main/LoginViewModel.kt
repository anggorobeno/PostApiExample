package com.example.learndaggerhilt.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learndaggerhilt.data.MainRepository
import com.example.learndaggerhilt.data.model.*
import com.example.learndaggerhilt.session.SessionManager
import com.example.learndaggerhilt.utils.NetworkHelper
import com.example.learndaggerhilt.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val sessionManager: SessionManager,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _users = MutableLiveData<Resource<String>>()
    val users: MutableLiveData<Resource<String>>
        get() = _users

    fun postLogin(request: LoginRequest) {
        viewModelScope.launch {
            try {
                _users.postValue(Resource.loading(null))
                if (networkHelper.isNetworkConnected()) {
                    mainRepository.postLogin(request).let {
                        if (it.isSuccessful) {
                            _users.postValue(Resource.success(it.message()))
                            sessionManager.saveAuthToken(it.body()!!.data.token)
                        } else
                            _users.postValue(Resource.error(it.message()))
                    }
                }
                else
                    _users.postValue(Resource.error("No internet Connection"))
            } catch (e: HttpException) {
                _users.postValue(Resource.error(e.message()))
            } catch (e: IOException) {
                _users.postValue(Resource.error(e.localizedMessage!!))
            }


        }

    }


}