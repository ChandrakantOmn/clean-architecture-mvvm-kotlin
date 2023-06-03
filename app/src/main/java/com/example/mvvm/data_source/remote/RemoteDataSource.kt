package com.example.mvvm.data_source.remote

import com.example.mvvm.entities.ResultData
import com.example.mvvm.entities.User

interface RemoteDataSource {
	suspend fun getUsers(): ResultData<List<User>>
}
