package com.example.mvvm.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm.R
import com.example.mvvm.databinding.ItemUserBinding
import com.example.mvvm.entities.User

class UsersListAdapter(
	var listStats: List<User>,
	val itemClickListener: ItemClickListener
) :
	RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {
	
	override fun getItemCount(): Int { return listStats.size }
	
	
	private lateinit var binding: ItemUserBinding
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return ViewHolder(binding)
	}
	
	/* override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val largeNews = listStats[position]
		holder.bind(largeNews)
	} */
	
	
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bindViewHolder(listStats[position])
	}
	
	inner class ViewHolder(private val viewDataBinding: ItemUserBinding) :
		RecyclerView.ViewHolder(viewDataBinding.root) {
		
		fun bindViewHolder(user: User) {
			viewDataBinding.tvProfileName.text = user.login
			viewDataBinding.tvReposUrl.text = user.reposUrl
			
			Glide.with(itemView.context)
				.load(user.avatarUrl)
				.placeholder(R.mipmap.ic_launcher)
				.into(viewDataBinding.ivProfilePic)
			viewDataBinding.userContainer.setOnClickListener {
				itemClickListener.onItemClick(user)
			}
			//viewDataBinding.userContainer.setOnClickListener {  } = countriesItemClickListener
		}
	}
	
	private var tempListCountries: List<User> = listStats
	
}