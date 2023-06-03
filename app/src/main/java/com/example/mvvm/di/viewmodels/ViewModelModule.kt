package com.example.mvvm.di.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.ui.list.UsersListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
	 @Binds
	@IntoMap
	@ViewModelKey(UsersListViewModel::class)
	abstract fun bindListStatsVM(ListCountriesStatsViewModel: UsersListViewModel): ViewModel
	
	@Binds
	abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
}
