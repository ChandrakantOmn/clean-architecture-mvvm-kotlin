package com.example.mvvm.repository

import com.example.mvvm.entities.ResultData
import com.example.mvvm.entities.User

interface UsersRepository {
	suspend fun getUsersList(): ResultData<List<User>>
}