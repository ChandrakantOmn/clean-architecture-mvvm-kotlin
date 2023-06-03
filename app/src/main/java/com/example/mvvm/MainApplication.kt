package com.example.mvvm
import android.app.Application
import com.example.mvvm.di.InternetUtil
import com.example.mvvm.di.componunt.AppComponents
import com.example.mvvm.di.componunt.DaggerAppComponents
import com.example.mvvm.di.modules.AppModule
import com.example.mvvm.di.modules.NetworkModule
import com.example.mvvm.di.modules.StorageModule
import com.facebook.stetho.BuildConfig
import com.facebook.stetho.Stetho
import timber.log.Timber

open class MainApplication : Application() {
	
	companion object {
		lateinit var appComponents: AppComponents
	}
	
	override fun onCreate() {
		super.onCreate()
		appComponents = initDagger(this)
		initStetho()
		initTimber()
		InternetUtil.init(this)
	}
	
	private fun initDagger(app: MainApplication): AppComponents =
		DaggerAppComponents.builder()
			.appModule(AppModule(app))
			.storageModule(StorageModule(app))
			.networkModule(NetworkModule(app))
			.build()
	
	private fun initStetho() {
		if (BuildConfig.DEBUG)
			Stetho.initializeWithDefaults(this)
	}
	
	private fun initTimber() {
		if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
	}
}