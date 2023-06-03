package com.example.mvvm.data_source.remote

import com.example.mvvm.entities.ResultData
import com.example.mvvm.entities.User
import com.example.mvvm.entities.UserList

interface RemoteDataSource {
	suspend fun getUsers(): ResultData<List<User>>
}
