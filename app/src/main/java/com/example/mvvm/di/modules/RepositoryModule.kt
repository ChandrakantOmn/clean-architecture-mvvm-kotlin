package com.example.mvvm.di.modules

import com.example.mvvm.api.UsersApiService
import com.example.mvvm.data_source.local.UsersDao
import com.example.mvvm.di.IoDispatcher
import com.example.mvvm.data_source.remote.RemoteDataSourceImpl
import com.example.mvvm.repository.UsersRepositoryImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton


@Module
class RepositoryModule {
	@Provides
	@Singleton
	fun provideAppRepository(
		@IoDispatcher ioDispatcher: CoroutineDispatcher,
		api: UsersApiService,
		appDao: UsersDao
	): UsersRepositoryImpl {
		val userDataSource = RemoteDataSourceImpl(api, ioDispatcher)
		return UsersRepositoryImpl(userDataSource, appDao, ioDispatcher)
	}
}