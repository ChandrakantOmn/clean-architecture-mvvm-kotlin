package com.example.mvvm.di.modules

import android.app.Application
import android.content.Context
import com.example.mvvm.di.viewmodels.ViewModelModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule(private val application: Application) {
	@Provides
	@Singleton
	fun providesContext(): Context {
		return application
	}
	
	
	
}