package com.example.mvvm.data_source.remote

import com.example.mvvm.api.UsersApiService
import com.example.mvvm.di.IoDispatcher
import com.example.mvvm.entities.ResultData
import com.example.mvvm.entities.UserList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RemoteDataSourceImpl(
	private val api: UsersApiService, @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RemoteDataSource {
	override suspend fun getUsers(): ResultData<UserList> = withContext(ioDispatcher) {
		val request = api.getUsers(0, 200)
		ResultData.Success(request)
	}
}


