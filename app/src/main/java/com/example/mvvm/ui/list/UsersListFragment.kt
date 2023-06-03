package com.example.mvvm.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mvvm.MainApplication
import com.example.mvvm.R
import com.example.mvvm.databinding.FragmentUsersListBinding
import com.example.mvvm.entities.User
import com.example.mvvm.hide
import com.example.mvvm.show
import com.example.mvvm.viewModelProvider
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class UsersListFragment : Fragment(), ItemClickListener, SwipeRefreshLayout.OnRefreshListener  {
	
	private val appComponents by lazy { MainApplication.appComponents }
	private lateinit var countriesAdapter: UsersListAdapter
	
	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory
	
	private fun getViewModel(): UsersListViewModel {
		return viewModelProvider(viewModelFactory)
	}
	private var _binding: FragmentUsersListBinding? = null
	private val binding get() = _binding!!
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		appComponents.inject(this)
		_binding = FragmentUsersListBinding.inflate(inflater, container, false)
		return binding.root
		
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		/* binding.buttonFirst.setOnClickListener {
			findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
		} */
		initViews()
		initObservers()
	}
	
	
	private fun initViews() {
		getViewModel().getUserLists()
		binding.swipeRefresh.setOnRefreshListener(this)
	}
	
	private fun initObservers() {
		getViewModel().resultListStats.observe(viewLifecycleOwner, Observer {
			binding.swipeRefresh.isRefreshing = false
			initRecycler(it)
		})
		getViewModel().errorMessage.observe(viewLifecycleOwner, Observer {
			binding.swipeRefresh.isRefreshing = false
			handleEmptyList()
		})
		getViewModel().showLoading.observe(viewLifecycleOwner, Observer { showLoading ->
			if (showLoading) binding.progress.show()
			else binding.progress.hide()
		})
	}
	
	private fun initRecycler(list: List<User>) {
		if (!list.isNullOrEmpty()) {
			countriesAdapter = UsersListAdapter(list, this)
			binding.recyclerListStats.apply {
				setHasFixedSize(true)
				layoutManager = LinearLayoutManager(activity)
				adapter = countriesAdapter
			}
		} else {
			handleEmptyList()
		}
	}
	
	private fun handleEmptyList() {
		with(binding) {
			recyclerListStats.hide()
			tvErrorMessage.show()
			tvErrorMessage.text = getString(R.string.first_fragment_label)
		}
	}
	
	
	override fun onItemClick(countryStat: Any) {
		/* Intent(activity, DetailsCountriesStatsActivity::class.java).apply {
					putExtra(COUNTRY_STATS_EXTRA, countryStat)
					startActivity(this)
				} */
	}
	
	override fun onRefresh() {
		getViewModel().getUserLists()
	}

	
	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}