package com.example.mvvm.api

import com.example.mvvm.di.modules.USERS
import com.example.mvvm.entities.User
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by srinivas on 2019-06-28.
 */
interface UsersApiService {
    @GET(USERS)
    suspend fun getUsers(@Query("since") since: Long, @Query("per_page") perPage: Int): List<User>
}
