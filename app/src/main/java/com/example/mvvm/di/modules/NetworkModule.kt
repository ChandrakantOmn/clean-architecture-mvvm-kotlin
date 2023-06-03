package com.example.mvvm.di.modules

import android.app.Application
import com.example.mvvm.api.UsersApiService
import com.facebook.stetho.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule (private val application: Application){
	@Provides
	@Singleton
	fun providesRetrofit(gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit {
		return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(gsonConverterFactory).client(okHttpClient).build()
	}
	
	@Provides
	@Singleton
	fun providesOkHttpClient(): OkHttpClient {
		val client = OkHttpClient.Builder()
			.connectTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
			.writeTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
			.readTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
		client.addInterceptor(ConnectivityInterceptorImpl(application))
		if (BuildConfig.DEBUG) client.addNetworkInterceptor(StethoInterceptor())
		return client.build()
	}
	
	@Provides
	@Singleton
	fun providesConverterFactory(): GsonConverterFactory {
		return GsonConverterFactory.create()
	}
	
	@Provides
	@Singleton
	fun provideApiService(retrofit: Retrofit): UsersApiService = retrofit.create(UsersApiService::class.java)
	
	
	
}

const val IO_TIMEOUT = 30L
const val BASE_URL = "https://api.github.com/"
const val USERS = "users"
