package com.example.mvvm.di.modules

import android.app.Application
import androidx.room.Room
import com.example.mvvm.data_source.local.UsersDao
import com.example.mvvm.data_source.local.AppDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class StorageModule(private val application: Application) {
	
	private var appDb: AppDb = Room.databaseBuilder(application, AppDb::class.java, "db").build()
	
	@Singleton
	@Provides
	fun providesRoomDatabase(): AppDb {
		return appDb
	}
	
	@Singleton
	@Provides
	fun providesAppDao(demoDatabase: AppDb): UsersDao {
		return demoDatabase.appDao()
	}
}