package com.example.mvvm.di.modules

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ConnectivityInterceptorImpl @Inject constructor(private var application: Application) : Interceptor {
	override fun intercept(chain: Interceptor.Chain): Response {
		isOnline()
		return chain.proceed(chain.request())
	}
	
	private fun isOnline(): Boolean {
		val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		var res = false
		connectivityManager.let {
			it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
				res = when {
					hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
					hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
					else -> false
				}
			}
		}
		if (!res){
			//Toast.makeText(application, "No network", Toast.LENGTH_LONG).show()
		}
		return res
	}
}
