package com.example.mvvm.di.componunt

import android.content.Context
import com.example.mvvm.ui.list.UsersListFragment
import com.example.mvvm.ui.home.MainActivity
import com.example.mvvm.di.modules.NetworkModule
import com.example.mvvm.ui.details.DetailFragment
import com.example.mvvm.data_source.local.UsersDao
import com.example.mvvm.data_source.local.AppDb
import com.example.mvvm.di.modules.AppModule
import com.example.mvvm.di.modules.ConnectivityInterceptorImpl
import com.example.mvvm.di.modules.CoroutinesModule
import com.example.mvvm.di.modules.RepositoryModule
import com.example.mvvm.di.modules.StorageModule
import dagger.Component
import okhttp3.Interceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
	modules = [
		AppModule::class,
		NetworkModule::class,
		RepositoryModule::class,
		CoroutinesModule::class,
		StorageModule::class
	]
)
interface AppComponents {
	fun context(): Context
	fun retrofit(): Retrofit
	fun appDao(): UsersDao
	fun appDatabase(): AppDb
	fun inject(mainActivity: MainActivity)
	fun inject(listCountriesStatsActivity: UsersListFragment)
	fun inject(worldStatsActivity: DetailFragment)
}