package com.example.mvvm.repository

import com.example.mvvm.data_source.local.UsersDao
import com.example.mvvm.data_source.remote.RemoteDataSource
import com.example.mvvm.di.InternetUtil
import com.example.mvvm.di.IoDispatcher
import com.example.mvvm.di.RemoteDataNotFoundException
import com.example.mvvm.entities.ResultData
import com.example.mvvm.entities.User
import com.example.mvvm.entities.UserList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class UsersRepositoryImpl(
	private val remoteDataSource: RemoteDataSource,
	private val appDao: UsersDao,
	@IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : UsersRepository {
	override suspend fun getUsersList(): ResultData<List<User>> {
		return when (val result =  remoteDataSource.getUsers()) {
			is ResultData.Success -> {
				val response = result.data
				//withContext(ioDispatcher) { appDao.setUserList(response) }
				appDao.setUserList(response)
				ResultData.Success(response)
			}
			is ResultData.Error -> {
				ResultData.Error(RemoteDataNotFoundException())
			}
		}
	}
	
}