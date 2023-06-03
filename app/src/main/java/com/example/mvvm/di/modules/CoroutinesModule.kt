package com.example.mvvm.di.modules


import com.example.mvvm.di.DefaultDispatcher
import com.example.mvvm.di.IoDispatcher
import com.example.mvvm.di.MainDispatcher
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
object CoroutinesModule {
	@DefaultDispatcher
	@JvmStatic
	@Provides
	fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
	
	@IoDispatcher
	@JvmStatic
	@Provides
	fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
	
	@MainDispatcher
	@JvmStatic
	@Provides
	fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}