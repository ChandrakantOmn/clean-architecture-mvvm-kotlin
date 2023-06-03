package com.example.mvvm.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.entities.ResultData
import com.example.mvvm.entities.User
import com.example.mvvm.repository.UsersRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

class UsersListViewModel @Inject constructor(
	private val repositoryImpl: UsersRepositoryImpl
) : ViewModel() {
	
	private var _mutableLiveData = MutableLiveData<List<User>>()
	var resultListStats: LiveData<List<User>> = _mutableLiveData
	
	private var _errorMessage = MutableLiveData<String>()
	var errorMessage: LiveData<String> = _errorMessage
	
	private var _showLoading = MutableLiveData<Boolean>()
	var showLoading: LiveData<Boolean> = _showLoading
	
	fun getUserLists() {
		_showLoading.postValue(true)
		viewModelScope.launch {
			try {
				when (val response = repositoryImpl.getUsersList()) {
					is ResultData.Success -> {
						_showLoading.postValue(false)
						_mutableLiveData.postValue(response.data)
					}
					is ResultData.Error -> {
						_showLoading.postValue(false)
						_errorMessage.postValue(response.exception.toString())
					}
				}
			} catch (e: Exception) {
				_showLoading.postValue(false)
				_errorMessage.postValue(e.message)
			}
		}
	}
}